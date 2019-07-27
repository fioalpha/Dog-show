# Dog Show

This project test proof.

## Description of a project

    1. Splash Screen have responsibility in know if user has done SingUp, if yes open ListDog screen or open SignUp screen 
    2. SignUp Screen do login of user, this screen is responsible for validating if email is validated
    3. ListDog Screen show list of dog lis, if click on item  open full the screen with image, have a bottom menu to navigate between dogs categories
    
## Architecture 

 I use MVVM with clear architecture and RX
 
### Layer 

#### View

    - Show component to user
    - Use ViewState to represent the state view. How *Loading*, *Error*, *Success*

#### ViewModel
     
     - Used to do strategies the controller view with Usecase
     
#### UseCase

    - Apply business rules

#### Data

    - Represent struct data

#### Repository
 
    - Orchestra data source and remote data source

##### LocalDataSource
 
    - Responsible to access the data saved on *Device* 
    - Use SharedPreference to save data 

##### RemoteDataSource
    
    - Responsible consumer data on internet
    - Use Retrofit to fetch data
    
 
## ScreenShot
  
 |  Splash | SignUp  | DogList  | Full  |
 |---------|---------|----------|-------|
 |  ![Splash Logo](/screenshot/splash_screen.png) | ![SignUp Screen](/screenshot/signup_screen.png)  |  ![DogList Screen](/screenshot/doglist_screen.png) |  ![Full Screen](/screenshot/full_screen.png) |

## Lib use 
 - Koin to inject dependencies
 - Mockk to Mock object to test
 - Retrofit to Restful
 - Gson to serialization object
 - Picasso to manage download image
 - Rx 
 
 
 ## LICENSE
 
 ```
 The MIT License (MIT)
 
 Copyright (c) 2018 Ubiratan Soares
 
 Permission is hereby granted, free of charge, to any person obtaining a copy of
 this software and associated documentation files (the "Software"), to deal in
 the Software without restriction, including without limitation the rights to
 use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 the Software, and to permit persons to whom the Software is furnished to do so,
 subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ```
