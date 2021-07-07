<p align="center">
    <img alt="WikiPills logo" width="128" height="128" src="../Web-module/public/img/icons/android-chrome-256x256.png">
</p>


# **WikiPills**


## Features

- 📞 An IVR-based virtual assistant that is available 24/7 over a dedicated phone number with no internet connection required (imagine Alexa but available on a phone call).
- 🌀 Flask backend to handle the Twilio API call requests and redirect them to the appropriate tasks using routing.
- 🔐 Local server communicates with the public internet over a secure tunnel via ngrok.


# Demo

- Aditi Voicebot [[video demo](https://youtu.be/2iwRkP7BQyw?t=318)]

# Installation

NOTE: You will have to register a number on Twilio's platform and configure it as explained [here](https://www.twilio.com/docs/autopilot/quickstart/python-quickstart#sign-up-for-twilio-and-get-a-phone-number).

1. This module requires Python >= 3.5. Run this command to ensure that you have the correct python version installed.
    ```bash
    python --version
    ```
2. Install Twilio Python SDK and Flask.
    ```bash
    python -m pip install -r requirements.txt
    ```
3. Test installation of Flask by running this command.
    ```bash
    flask run
    ```
4. Download ngrok from [here](https://ngrok.com/download).
5. Run ngrok using this command (make sure that the Flask server is running).
    ```bash
    ./ngrok http 3000
    ```
6. Replace the URL in `dynamicsay.json` with your new HTTPS URL from ngrok.
    ```json
    "redirect": "<enter_https_url_here>/collect"
    ```
7. Similar changes will have to be made on the Twilio Autopilot console for the task-specific JSON files (only if any task calls the server).


# Contributors

See [CONTRIBUTORS.md](../CONTRIBUTORS.md).
