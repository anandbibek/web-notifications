import json
import requests
from bs4 import BeautifulSoup


def get_page(page_url):
    """Fetch content of page into BS4"""

    headers = {'User-Agent': 'Mozilla/5.0'}
    page = requests.get(page_url, headers=headers, verify=False, timeout=10)
    return BeautifulSoup(page.content, "lxml")


def get_links(soup, div_class):
    """Function fetching data from <a>.text() in given div"""

    # Use XPath to select elements
    page_notices = soup.find_all("div", class_=div_class)
    new_notices = []

    # Iterate over the selected elements
    for notice in page_notices:
        # Find anchor tags within each element
        for link in notice.find_all('a'):
            new_notices.append(link.get_text().strip())

    return new_notices


def get_texts(soup, div_class):
    """Function fetching text() from given div"""

    new_notices = []
    page_news = soup.find_all("div", class_=div_class)
    for news in page_news:
        new_notices.append(sanitise(news.get_text()))

    return new_notices


def sanitise(val):
    """ Cleanup unwanted characters from data"""
    return val.strip("\n").replace("\n\n", "-").replace("\n", " ")


def combine_arrays(*arrays):
    """Combine arrays into json data"""

    combined_array = []
    for array in arrays:
        combined_array.extend(array)
    return json.dumps({'data': combined_array})
