import os
import json
from google.cloud import storage

# Create storage_client instance outside of functions
storage_client = storage.Client()


def is_running_on_cloud():
    """Check if the code is running on a cloud platform (GCP)."""
    return "K_SERVICE" in os.environ


def write_data_to_file(bucket_name, filename, data):
    """Write data to a file in the bucket or locally."""
    if is_running_on_cloud():
        # Write to GCS
        bucket = storage_client.bucket(bucket_name)
        blob = bucket.blob(filename)
        blob.upload_from_string(json.dumps(data))
    else:
        # Write locally
        with open(filename, "w") as file:
            json.dump(data, file)


def read_data_from_file(bucket_name, filename):
    """Read data from a file in the bucket or locally."""
    if is_running_on_cloud():
        # Read from GCS
        bucket = storage_client.bucket(bucket_name)
        blob = bucket.blob(filename)
        return json.loads(blob.download_as_text())
    else:
        # Read locally
        try:
            with open(filename, "r") as file:
                return json.load(file)
        except FileNotFoundError:
            return ""
