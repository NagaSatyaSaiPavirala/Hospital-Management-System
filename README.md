# Created by Naga Satya Sai Pavirala with JUnit Code Coverage:
Class 100%
Method 93%
Line 88%


# Creating hospital-management-system microservice
https://start.spring.io/

# Building the project
mvn clean install

# Running microservice by building docker image
mvn install dockerfile:build
by using the above command the docker image is successfully built

docker-compose -f docker-compose.yml up -d
this is used to run the built docker image in docker desktop

docker ps
this command is use to verify the docker image we built is running in the docker container or not after running it we see two images
satya4236/hospital-management-system:1.0.0
hospital-management-system-mongo-1

docker login
we can use above command to login to docker hub

docker push satya4236/hospital-management-system:1.0.0
we can push the image into our repository using the above command

# We can post and get data into the mongodb by using postman or swagger ui for graphical user interface

# Swagger url
http://localhost:8081/swagger-ui/index.html

# Authentication for swagger login
Role:DOCTOR  Username:doctor01	Password - password

Role:PATIENT Username:patient01	Password - password

Role:DOCTOR,PATIENT  Username:admin01	Password - password

# Testing application using Postman

# Prescription Controller
/saveprescription
Role:DOCTOR,PATIENT  Username:admin01	Password - password
Curl command:
curl --location --request POST 'http://localhost:8081/saveprescription' \
--header 'Authorization: Basic YWRtaW4wMTpwYXNzd29yZA==' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=9DEA21124C9AB6586CE6D69DC30F432A' \
--data-raw '{
    "prescriptionId":"prescription1",
    "appointmentId":"appointment1",
    "description":"prescription1",
    "patientName":"patient1",
    "doctorName":"doctor1"
}'


/viewprescription
Role:DOCTOR,PATIENT  Username:admin01	Password - password
Curl command:
curl --location --request GET 'http://localhost:8081/viewprescription?patientName=patient1' \
--header 'Cookie: JSESSIONID=9DEA21124C9AB6586CE6D69DC30F432A'

# Doctor Controller
/save
Role:DOCTOR  Username:doctor01	Password - password
Curl command:
curl --location --request POST 'http://localhost:8081/doctor/save' \
--header 'Authorization: Basic ZG9jdG9yMDE6cGFzc3dvcmQ=' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=9DEA21124C9AB6586CE6D69DC30F432A' \
--data-raw '{
    "appointmentId":"appointment1",
    "patientName":"patient1",
    "doctorName":"doctor1",
    "date":"29/09/2022",
    "prescription":{
     "prescriptionId":"prescription1",
     "appointmentId":"appointment1",
     "description":"description1",
     "patientName":"patient1",
     "doctorName":"doctor1"
    }
}'

/doctorappointment
Role:DOCTOR  Username:doctor01	Password - password
Curl command:
curl --location --request GET 'localhost:8081/doctor/doctorappointment?doctorName=doctor1' \
--header 'Authorization: Basic ZG9jdG9yMDE6cGFzc3dvcmQ=' \
--header 'Cookie: JSESSIONID=9DEA21124C9AB6586CE6D69DC30F432A'

# Patient Controller
/save
Role:PATIENT Username:patient01	Password - password
Curl command:
curl --location --request POST 'localhost:8081/patient/save' \
--header 'Authorization: Basic cGF0aWVudDAxOnBhc3N3b3Jk' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=9DEA21124C9AB6586CE6D69DC30F432A' \
--data-raw '{
    "appointmentId":"appointment1",
    "patientName":"patient1",
    "doctorName":"doctor1",
    "date":"29/09/2022",
    "prescription":{
     "prescriptionId":"prescription1",
     "appointmentId":"appointment1",
     "description":"description1",
     "patientName":"patient1",
     "doctorName":"doctor1"
    }
}'

/myapointment
Role:PATIENT Username:patient01	Password - password
Curl command:
curl --location --request GET 'localhost:8081/patient/myappointment?patientName=patient1' \
--header 'Cookie: JSESSIONID=9DEA21124C9AB6586CE6D69DC30F432A'

# We can see data stored in mongo db by using mongo bash in our commandprompt or we can use Studio 3T for graphical user interface

# Seeing data in mongo db
docker exec -it hospital-management-system_mongo_1 bash

mongo

show dbs

use database

show tables
op:
appointment
prescription

db.appointment.find()
{ "_id" : ObjectId("633576852ca330563ec4c784"), "appointmentId" : "appointment1", "patientName" : "patient1", "doctorName" : "doctor1", "date" : "29/09/2022", "prescription" : { "prescriptionId" : "prescription1", "appointmentId" : "appointment1", "description" : "description1", "patientName" : "patient1", "doctorName" : "doctor1" }, "_class" : "com.satya.app.model.Appointment" }
{ "_id" : ObjectId("6335787e2ca330563ec4c785"), "appointmentId" : "appointment1", "patientName" : "patient1", "doctorName" : "doctor1", "date" : "29/09/2022", "prescription" : { "prescriptionId" : "prescription1", "appointmentId" : "appointment1", "description" : "description1", "patientName" : "patient1", "doctorName" : "doctor1" }, "_class" : "com.satya.app.model.Appointment" }

db.prescription.find()
{ "_id" : ObjectId("6335726c2ca330563ec4c783"), "prescriptionId" : "prescription1", "appointmentId" : "appointment1", "description" : "prescription1", "patientName" : "patient1", "doctorName" : "doctor1", "_class" : "com.satya.app.model.Prescription" }

# To stop the container
docker-compose -f docker-compose.yml down


