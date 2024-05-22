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

Updates

22/5/2022 --> Communicate with flask API to predict a device (DONE)

HOW TO RUN =)

1) open vs-code
2) Run flask app using trminal
```
flask run
```
3) Run springboot app 

4) use postman to test end-points
- POST /api/devices/: Retrieve a list of all devices
- GET /api/devices/{id}: Retrieve details of a specific device by ID.
- POST /api/devices: Add a new device.
- POST /api/predict/{deviceId}: This will call the Python API to predict the price, and save the result in the device entity here.

