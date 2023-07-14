#!/bin/sh

export project="cloud-func-test-392314"
export function_name="web-notifications"
export job_name="web-notification-schedule"
export location="us-central1"
export schedule="0 7-23/2 * * *" # every day, every 2 hours from 07:00 to 23:00
export timezone="Asia/Kolkata"

export action="nita"