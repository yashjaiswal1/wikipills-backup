from twilio.twiml.voice_response import Dial, VoiceResponse, Say

response = VoiceResponse()
response.dial('799-352-9863')
response.say('Goodbye')

print(response)