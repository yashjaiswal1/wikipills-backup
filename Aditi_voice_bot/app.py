from flask import Flask

app = Flask(__name__)


@app.route('/')
def home():
    return 'Hello World!'

from flask import send_file

@app.route('/dynamicsay', methods=['POST'])
def dynamic_say():
    return send_file('dynamicsay.json')

import json
from flask import jsonify, request

@app.route('/collect',  methods=['POST'])
def collect():
    memory = json.loads(request.form.get('Memory'))

    answers = memory['twilio']['collected_data']['collect_clothes_order']['answers']

    # # first_name = answers['first_name']['answer']
    # # num_clothes = answers['num_clothes']['answer']
    # shipping_country = answers['shipping_country']['answer']
    friend = answers['friend']['answer']

    message = (
        # f'Ok {first_name}. Your order for {num_clothes} clothes is now confirmed and will be shipping to {shipping_country}.'
        # f' Thank you for ordering with us'
        f'Okay, I will call {friend} right away!'
    )

    return jsonify(actions=[{'say': {'speech': message}}])

@app.route('/dynamicsay_greeting', methods=['POST'])
def dynamic_say_greeting():
    return send_file('dynamicsay_greeting.json')