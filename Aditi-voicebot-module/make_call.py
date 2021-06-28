# Download the helper library from https://www.twilio.com/docs/python/install
from twilio.rest import Client


# Your Account Sid and Auth Token from twilio.com/console
# DANGER! This is insecure. See http://twil.io/secure
account_sid = 'AC8a739c499ccebbc1b82e3a20d2a013d1'
auth_token = 'b66174536dc4db17145b72563ca4dc33'
client = Client(account_sid, auth_token)

call = client.calls.create(
                        url='http://demo.twilio.com/docs/voice.xml',
                        to='+919826151216',
                        from_='+13344215817'
                    )

print(call.sid)
