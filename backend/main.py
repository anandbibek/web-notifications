import flask
import functions_framework

from scraper import scan_nita


@functions_framework.http
def scan(request: flask.Request) -> flask.Response:
    """checks website for new notices

    Returns:
        JSON data of new notices since last run
    """

    return scan_nita()
