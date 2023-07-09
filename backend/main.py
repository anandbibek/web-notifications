# Copyright 2018 Google LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# [START gae_python3_app]
from flask import Flask, jsonify

from backend.common.comparator import get_diff_array
from backend.common.storage import read_data_from_file, write_data_to_file
from common.scraper import get_links, get_page, get_texts, combine_arrays

# If `entrypoint` is not defined in app.yaml, App Engine will look for an app
# called `app` in `main.py`.
app = Flask(__name__)

# Constants
BUCKET_NAME = "data-bucket"


@app.route("/")
def hello():
    """Return a friendly HTTP greeting.

    Returns:
        A string with the words 'Hello World!'.
    """

    return 'Hello World'


@app.route("/nita")
def hello_nita():
    """Return a friendly HTTP greeting.

    Returns:
        A string with the words 'Hello World!'.
    """
    filename = 'nita.data.json'
    soup = get_page("https://nita.ac.in/")
    notices = get_links(soup, "notice_board_overflow")
    news = get_texts(soup, "news_card")
    data_new = combine_arrays(notices, news)
    data_prev = read_data_from_file(BUCKET_NAME, filename)
    data_diff = get_diff_array(data_new, data_prev)

    if len(data_diff) > 0:
        write_data_to_file(BUCKET_NAME, filename, data_new)

    return jsonify(data=data_diff)


if __name__ == "__main__":
    # This is used when running locally only. When deploying to Google App
    # Engine, a webserver process such as Gunicorn will serve the app. You
    # can configure startup instructions by adding `entrypoint` to app.yaml.
    app.run(host="127.0.0.1", port=8080, debug=True)

# [END gae_python3_app]
