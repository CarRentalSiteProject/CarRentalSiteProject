<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<head>
<title>SignUp</title>
</head>
    <body class="text-muted">
        
        <main>
            <section class="bg-secondary pb-5 position-relative poster pt-5 text-white-50">
                <div class="container mt-5 pb-5 pt-5">
                    <div class="mt-5 pt-5 row">
                        <div class="col-md-10 col-xl-7 pt-5">
                            <p class="fw-bold h4 text-white">Car Rentals</p>
                            <h1><span class="display-3 fw-bold mb-4 text-primary">Sign </span><span class="text-white">Up</span></h1>
                            <div class="bg-white p-4">
                                <h2 class="fw-bold h5 mb-3 text-dark">Create an Account</h2>
								<form role="form" onsubmit="validateForm(event)" action="signup" method="post"> 
								    <div class="align-items-center gx-2 gy-3 row">
								        <div class="col-6"> 
								            <label class="text-primary">Name: </label>
								            <input type="text" class="form-control pb-2 pe-3 ps-3 pt-2 rounded-0" name="name" placeholder="Tom"> 
								        </div>
								        <div class="col-6"> 
								            <label class="text-primary">Age: </label>
								            <input type="text" class="form-control pb-2 pe-3 ps-3 pt-2 rounded-0" name="age" placeholder="18"> 
								        </div>
								        <div class="col-6"> 
								            <label class="text-primary">Gender: </label>
								            <div class="text-primary">
								                <input id="genderMale" type="radio" name="gender" value="male" class="form-check-input">
								                <label class="form-check-label" for="genderMale">Male</label>
								            </div>
								            <div class="text-primary">
								                <input id="genderFemale" type="radio" name="gender" value="female" class="form-check-input">
								                <label class="form-check-label" for="genderFemale">Female</label>
								            </div>
								            <div class="text-primary">
								                <input id="genderOther" type="radio" name="gender" value="other" class="form-check-input">
								                <label class="form-check-label" for="genderOther">Other</label>
								            </div>
								        </div>
								        <div class="col-6"> 
								            <label class="text-primary">Phone: </label>
								            <input type="text" class="form-control pb-2 pe-3 ps-3 pt-2 rounded-0" name="phone" placeholder="09XX-XXX-XXX"> 
								        </div>
								        <div class="col-12"> 
								            <label class="text-primary">Email: </label>
								            <input type="email" class="form-control pb-2 pe-3 ps-3 pt-2 rounded-0" name="email" placeholder="tom@test.com"> 
								        </div>
								        <div class="col-6"> 
								            <label class="text-primary">Password: </label>
								            <input type="password" id="password" class="form-control pb-2 pe-3 ps-3 pt-2 rounded-0" name="password"> 
								        </div>
								        <div class="col-6"> 
								            <label class="text-primary">Confirm Password: </label>
								            <input type="password" id="confirmPassword" class="form-control pb-2 pe-3 ps-3 pt-2 rounded-0" name="confirmPassword"> 
								        </div>
								        <div class="col-12"> 
								            <label class="text-primary">Address: </label>
								            <input type="text" class="form-control pb-2 pe-3 ps-3 pt-2 rounded-0" name="address" placeholder="Taipei"> 
								        </div>
								        <div class="col-6"> 
								            <label class="text-primary">License Number: </label>
								            <input type="text" class="form-control pb-2 pe-3 ps-3 pt-2 rounded-0" name="licenseNub" placeholder="A1XXXXXXXX"> 
								        </div>
								        <div class="col-12 text-end"> 
								            <button type="submit" class="btn btn-primary pb-2 pe-4 ps-4 pt-2">Create</button>                                             
								        </div>
								    </div>
								</form>

                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        
</html>