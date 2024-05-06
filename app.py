from flask import Flask, request, jsonify
import joblib
import numpy as np
from tensorflow import keras
import tensorflow as tf

app = Flask(__name__)

# Load the model
#model = joblib.load( 'model\device_svm_model.pkl')
model = tf.keras.models.load_model(r'model\device_keras_model.h5')

@app.route("/")
def home():
    return "Hello, Flask load Model =)"

@app.route('/test', methods=['GET'])
def test():
    return "Hello, Flask Predict Get here =)"

@app.route('/predict', methods=['POST'])
def predict():

    # Get input data from the request
    
    data = request.json
    features = [float(x) for x in request.json.values()]
    final_features = np.array(features[1:21]).reshape(1, -1)

    # print(f"input_data: {final_features}")
    # Make a prediction
    prediction = model.predict(final_features)
    price = prediction.argmax()
   # print(f"prediction: {prediction}")
   # print(f"This device belongs to: {price} class ")
    

    # Return the prediction as JSON
    return jsonify({"price_range": int(price)})
    # f"This device belongs to: {price}"
#jsonify({"prediction": prediction[0]})