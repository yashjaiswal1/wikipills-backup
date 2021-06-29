import json
from flask import send_file
from flask import jsonify, request
from flask import Flask

app = Flask(__name__)


@app.route('/')
def home():
    # testing server
    return 'Hello World!'


@app.route('/dynamicsay', methods=['POST'])
def dynamic_say():
    return send_file('dynamicsay.json')


@app.route('/collect',  methods=['POST'])
def collect():
    memory = json.loads(request.form.get('Memory'))

    answers = memory['twilio']['collected_data']['collect_help_procedure']['answers']
    friend = answers['friend']['answer']

    message = (
        f'Okay, I will call {friend} right away!'
    )

    return jsonify(actions=[{'say': {'speech': message}}])


@app.route('/dynamicsay_greeting', methods=['POST'])
def dynamic_say_greeting():
    return send_file('dynamicsay_greeting.json')
