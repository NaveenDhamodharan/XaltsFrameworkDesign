# XaltsFrameworkDesign

# I made regression group to run scripts with the new User, to run this follow below steps
   1. Go to testng-NewUser.xml file
   2. right click on this and select runas -> Testng suit
   3. this will run the flow for new user and it will skip existing user steps

# I made regressionOne group to run scripts with the existing User, to run this follow below steps
   1. Go to testng-ExistingUser.xml file
   2. right click on this and select runas -> Testng suit
   3. this will run the flow for existing user and it will skip new user steps

# NOTE: if you are running regression group ( means testng-NewUser.xml file), kindly update userId in TestScript java file before running the suite.
