System Main Component

1) Train Model with python
2) Deploy Model With Flask API :
  
   app.py  (predict price given a specification)

   "http://127.0.0.1:5000/predict"

   expect input as database device structure --> {id , 20 features , price}
  
3) Create Mysql DataBase
4) Create Springboot API:
  - create Device
  - Return all devices
  - Retern Device classification given an id
  - Communicate with flask API to predict a device (still n progress)
