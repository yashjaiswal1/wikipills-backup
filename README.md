<p align="center">
    <img alt="WikiPills logo" width="128" height="128" src="./public/android-chrome-256x256.png">
</p>

# **WikiPills**

An integrated software platform for monitoring and enhancing the **Healthcare** of **Kids, Senior Citizens** and the **Specially-abled**.

This project was made for **Smart India Hackathon 2020** under **Amazon Web Services** (Problem statement: [AN319](http://awssih2020.com/)).

Our team **H4CK3RS** was declared winners and ended up scoring the highest points amongst all finalists under the organization Amazon Web Services (AWS).

# Tech Stack

**Web:** HTML/CSS, Javascript, Bootstrap, heatmaps.js library, Google Maps API, chart.js, Firebase (web-hosting) <br>
**Database:** AWS DynamoDB <br>
**Mobile Application:** Android Studio, Material Design libraries <br>
**Aditi Voice Bot:** Flask (for routing tasks and handling API calls), ngrok, Twilio <br>
**Alexa Skill:** Alexa Developer Console <br>
**AR module:** Vuforia via Unity

## **Web Application**

The Web Dashboard is used for monitoring the concerned individual. It has 4 main components:

<!-- Open the [repository](https://github.com/yashjaiswal1/AN319_H4CK3RS_SIH2020)

1. Navigate to the **public** directory
2. Open the file
>index.html
3. Find the login credentials below.
4. Log-In with the desired user.

### Log-In using the respective credentials on Admin Dashboard
---
Doctor Login:
ID: doctor
Pass: doctor123

Relative/Guardian Login:
ID: relative
Pass: relative123   -->

<!-- #### Demo Links: -->
<!-- [Web-App](https://wiki-pill.web.app/index.html "Log-In")   -->

[Multi-spot Geofencing](https://wiki-pill.web.app/child/index.html "Geofence")  
[Electronic Health Record](https://wiki-pill.web.app/patients.html "Health Profile") <br>
[Analytics generated from crowdsourced data](https://wiki-pill.web.app/dashboard.html "Analytics")  
[Epidemic Vizualization](https://wiki-pill.web.app/public.html "Heatmap")

## **AWS DynamoDB**

The following credentials have to be updated with your own DynamoDB credentials:

**aws_access_key_id**  
**aws_secret_access_key**  
**aws_session_token**

## **Mobile-Application**

1. Open the [repository](https://github.com/yashjaiswal1/AN319_H4CK3RS_SIH2020)
2. Navigate to the `Andriod Application/` directory.
3. Download and Install the `WikiPills.apk` file.

**Note:** Make sure to give Camera permissions for the smooth working of the app.

### _Pulse Oximeter_

1. Launch the app.
2. Open the Heart Rate Monitor in the Mobile-app
3. Wait for the camera flash to switch on.
4. Place your finger on the camera lens and wait for result.

### _Multi-lingual and Dyslexic Interfaces_

The app supports Multi-lingual and Dyslexic interfaces. To use the interface of your preference follow the following steps:

1. Open the app.
2. On the top find the Language section. Click on it.
3. Select interface of your choice.

### _Fall Detection_

The app is endorsed with a fall detection algorithm.  
To test it follow these steps:

1. Open the app.
2. On the top find a `TEST` button. Tap on it.
3. An interface opens with a graph tracking the activity of the device.
4. Any sudden movments or jerks to the phone will be registered and the fall detection routine is executed.

## **Aditi Voice Bot**

Phone Number: **+1 334-421-5817**

Aditi is a voice assistant but unlike conventional voice assistants, it is **hosted on an actual phone number** (imagine Alexa but on a phone call). This increases the whole accessibility and scalability factor of this module by a huge margin.

**Note:** Make sure your phone has international calling balance to use the Voice bot.

## **Augmented Reality Module**

1. Download the file `AR.unitypackage` from the [repository](https://github.com/yashjaiswal1/AN319_H4CK3RS_SIH2020)
2. Run the file on the unity studio.
3. Point at image target to see AR pop-up.

**Note:** Make sure to use `Unity version 2019.1.12f1`. Other versions may not support the build.

## **Alexa Skill**

A specially developed skill for alexa enabled devices that provides the second vocal interface. Again it is aimed at improving accessibility and ease of use.
