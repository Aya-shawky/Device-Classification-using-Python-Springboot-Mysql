from flask import Flask, request, jsonify
import joblib
import numpy as np
from tensorflow import keras
import tensorflow as tf
import pandas as pd
from sklearn.preprocessing import StandardScaler
from sklearn.compose import make_column_transformer

import sklearn
print(sklearn.__version__)

app = Flask(__name__)

# Load the model
#model = joblib.load( 'model\device_svm_model.pkl')
model = tf.keras.models.load_model(r'device_keras_model.h5')
transformer = joblib.load(r"data_transformer.joblib")

@app.route('/predict', methods=['POST'])
def predict():

    # Get input data from the request
    
    row_data = pd.DataFrame([request.get_json()])

    #print(row_data.info())  #--------- [id , 19 Device Features , Price]
    #features = [float(x) for x in request.json.values()]
    #final_features = np.array(features[1:21]).reshape(1, -1)
    #print(transformer.transform(row_data))
    features = transformer.transform(row_data)
    #print(features)
    # Make a prediction
    prediction = model.predict(features)
    price = prediction.argmax()

    # Return the prediction as JSON
    return jsonify({"price_range": int(price)})
