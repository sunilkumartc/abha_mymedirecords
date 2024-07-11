import requests
import datetime

# Define the URL and headers
url = "https://abhasbx.abdm.gov.in//abha/api/v3/enrollment/request/otp"  # Replace with your actual URL and endpoint
timestamp = datetime.datetime.utcnow().isoformat() + "Z"
request_id = "ba150a31-9fda-43c4-a0ab-486f5c0860d9"
token = 'eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJBbFJiNVdDbThUbTlFSl9JZk85ejA2ajlvQ3Y1MXBLS0ZrbkdiX1RCdkswIn0.eyJleHAiOjE3MjA0MjgwNDQsImlhdCI6MTcyMDQyNjg0NCwianRpIjoiOTc5ZTcwYzktNjYyMS00OTgxLTgzNzMtMWQ1NzBhMTAxNzZmIiwiaXNzIjoiaHR0cHM6Ly9kZXYubmRobS5nb3YuaW4vYXV0aC9yZWFsbXMvY2VudHJhbC1yZWdpc3RyeSIsImF1ZCI6WyJhY2NvdW50IiwiU0JYVElEXzAwNjU3NiJdLCJzdWIiOiI3NmQ4MDVmZi02NjlkLTQwZDItYTRjZi0yMmYxNTI3MTA5Y2YiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJTQlhJRF8wMDc3MTEiLCJzZXNzaW9uX3N0YXRlIjoiMDcwZDlhYjctOGE5ZS00Y2M3LWIyMTMtMzhhMDc4ZTIyZGJhIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjkwMDciXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImhpdSIsIm9mZmxpbmVfYWNjZXNzIiwiaGVhbHRoSWQiLCJwaHIiLCJPSURDIiwiaGVhbHRoX2xvY2tlciIsImhpcCJdfSwicmVzb3VyY2VfYWNjZXNzIjp7IlNCWElEXzAwNzcxMSI6eyJyb2xlcyI6WyJ1bWFfcHJvdGVjdGlvbiJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19LCJTQlhUSURfMDA2NTc2Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgZW1haWwgcHJvZmlsZSIsImNsaWVudEhvc3QiOiIxMDAuNjUuMTYwLjIxNCIsImNsaWVudElkIjoiU0JYSURfMDA3NzExIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzZXJ2aWNlLWFjY291bnQtc2J4aWRfMDA3NzExIiwiY2xpZW50QWRkcmVzcyI6IjEwMC42NS4xNjAuMjE0In0.ecc1yQeZBFzxcynnG5bzGQqv0e7yDBmNF1lovs0BrmhkeCFF_UAE93ztv0sNzeKbtRLWzLkOaBrbA2YmZ7oEvjlls7Pa8zM6VTjdKzyqwfB5dLxOD76t-IcOEbxr0Gos_d0hqUxA1oaXKPV_DtZJ7pxHAdVPBquGt_3x1LemH5jRQH3KNav5n2N5X8VS6RL5veFtAU79rhuNrlDY5Hqbc-NoMLRtjO1vHcQXyN523-WrPCxBUhvsbnrwna6GUwKEbfM9Tn8XEAcyZ9R46bHd4gO2hWmxUwqqTw3-o9gacHM2YLlcoEc-m61FpvqSbqrWKMSjOlq3xNAjPuiIDlK5MQ'
headers = {
    "Content-Type": "application/json",
    "Authorization": f"Bearer {token}",
    "TIMESTAMP": timestamp,
    "REQUEST-ID": request_id
}

# Define the request body
request_body = {
    "txnId": "",
    "scope": ["abha-enrol"],
    "loginHint": "aadhaar",
    "loginId": "KbSc76bNQW10mkxb93T/1N0T7vG5E/TjRHDPkxbeuUSOADJMurEsq/tIBZN88txZEsPSO4dD/NwXzfFbHj2qZa9wQhIoUKxUzFf189f/60cvLJmU8NsUngUQpqRFdmRc8UhtGDOZWwtnS3n6aoTleE1O8vMz6Qcw+rVdAHcYsHZjVPH35/CujC3LY8LPJZ8KtCPOCp2r+otGaG1jYZLg1cXMbnzvSAHzDKJDbG64EUCZJzscjd3IDtwWgDA6e2xfEnoSAbBDqH9HVj/kgGd3TEVNXuaGAvzfb83ecQVdm7CsOK0hNpCzSdsbif5ItRqSuxX3NsJZDh+snQx9U8msR0DHNjUeMoXLRPE8V7wWjTU82h9YjPUfNWYll9zOSP3kZjHdytCsKUE+N8hbYRTif4LLWar6/LhAjxcjAHlRWPdmc2IXc92tq1cwQ+fDKXgLlPFRiuEalOm3dju0+ReI9+jUf+ssuzjxxJgwRmwuKG+B4Nz8WU5m/bMijm48Vmq8K04YDvlKNb11JrTl9uwMiQ3so7quLI6lM3viZy/os4klAWNKuqNDqcR4ADsHPoRN6tB+eBhHPTI0MhbwH9hGt43tnja9ndvDiHdaNABnHctPkAguNjy+y29dMm3U3INY182FbP57CkJSpsGDaS7NTE8wQ8kpZoyTPZC5AXx+r8A=",
    "otpSystem": "aadhaar"
}

# Send the POST request
response = requests.post(url, headers=headers, json=request_body)

# Print the response
if response.status_code == 200:
    print("Response:", response.json())
else:
    print("Error response code:", response.status_code)
    print("Error response body:", response.text)
