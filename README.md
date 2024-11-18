## Uber-like Project API Documentation
Overview
This Uber-like project allows users to sign up as riders or drivers. Riders can request rides, and drivers can accept, start, and complete rides. The system also supports OTP verification, surge pricing during late hours, and payments through wallets or cash. The application uses OSMR geolocation to track pickup and drop-off locations.

## Features:
Riders can request rides, cancel them, rate drivers, and track their ride status.
Drivers can accept, start, and complete rides, rate riders, and manage their profile.
Admins can onboard new drivers and manage user roles.
## API Endpoints
#Authentication Endpoints:
POST /auth/signup
Sign up with name, email, and password. (Password is stored securely using BCrypt.)

POST /auth/login
Login with email and password. Returns an access token.

POST /auth/onBoardNewDriver/{userId}
Admin endpoint to onboard a new driver. Requires userId and vehicleId.

POST /auth/refresh
Refresh the access token using the refresh token.

# Driver Endpoints:
POST /drivers/acceptRide/{rideRequestId}
Driver accepts a ride by providing the rideRequestId.

POST /drivers/startRide/{rideRequestId}
Driver starts the ride after validating the OTP.

POST /drivers/endRide/{rideId}
Driver ends the ride once completed.

POST /drivers/cancelRide/{rideId}
Driver cancels the ride (if assigned to the ride).

POST /drivers/rateRider
Driver rates the rider.

GET /drivers/getMyProfile
Driver views their profile, including rides and ratings.

GET /drivers/getAllMyRides
Driver views all rides they’ve been part of.

# Rider Endpoints:
POST /riders/requestRide
Rider requests a ride with pickup and drop-off locations, payment method, and other details.

POST /riders/cancelRide/{rideId}
Rider cancels the ride.

POST /riders/rateDriver
Rider rates the driver.

GET /riders/getMyProfile
Rider views their profile and ride history.

GET /riders/getAllMyRides
Rider views all rides they’ve taken.

# Error Handling & Response Codes
200 OK
Request successful.

201 Created
Resource created successfully.

400 Bad Request
Invalid request.

401 Unauthorized
Authentication required.

403 Forbidden
Insufficient permissions.

404 Not Found
Resource not found.

409 Conflict
Conflict with the request (e.g., user already exists).

500 Internal Server Error
Server error.

## Common Errors
Email Already Exists:
"Cannot signup, User already exists with email"

User Already a Driver:
"User with id: {userId} is already a driver"

Driver Cannot Cancel Ride:
"Driver can't cancel ride, they have not been assigned this ride"

Invalid OTP:
"OTP is not valid: {otp}"

Unauthorized:
"You are not authorized to access this resource"

Authentication & Authorization
The system uses JWT tokens for authentication. Access tokens expire after a set period, and a refresh token is required to obtain a new access token.
