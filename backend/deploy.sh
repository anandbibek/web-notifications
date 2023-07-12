#!/bin/sh

echo "Deploying..."

gcloud functions deploy web-notifications \
  --gen2 \
  --runtime=python311 \
  --region=us-central1 \
  --source=. \
  --entry-point=scan \
  --trigger-http \
  --allow-unauthenticated \
  --ingress-settings internal-only \

