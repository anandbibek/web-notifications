#!/bin/bash

delete_scheduler_job_if_exists() {
    job_name="$1"
    location="$2"

    gcloud scheduler jobs describe "$job_name" --location "$location" >/dev/null 2>&1
    if [ $? -eq 0 ]; then
        gcloud scheduler jobs delete "$job_name" --location "$location" --quiet
    else
        echo "[$job_name]: Does not exist."
    fi
}

create_scheduler_job() {
    job_name="$1"
    schedule="$2"
    timezone="$3"
    target_http_url="$4"
    location="$5"

    gcloud scheduler jobs describe "$job_name" >/dev/null 2>&1
    if [ $? -eq 0 ]; then
        echo "[$job_name]: Already exists. Skipping creation."
    else
        gcloud scheduler jobs create http "$job_name" \
            --schedule "$schedule" \
            --time-zone "$timezone" \
            --http-method "POST" \
            --uri "$target_http_url" \
            --location "$location" \

        if [ $? -eq 0 ]; then
          echo "[$job_name]: Created successfully."
        fi
    fi
}

job_name="web-notification-schedule"
location="us-central1"
echo "[$job_name]: Reconfiguring..."

# Delete the job if it exists
delete_scheduler_job_if_exists "$job_name" $location

# Create a new job if it doesn't exist
schedule="0 7-23/2 * * *"  # every day, every 2 hours from 07:00 to 23:00
timezone="Asia/Kolkata"
target_http_url="https://us-central1-cloud-func-test-392314.cloudfunctions.net/web-notifications"
create_scheduler_job "$job_name" "$schedule" "$timezone" "$target_http_url" "$location"
