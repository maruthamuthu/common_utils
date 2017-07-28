# utils

### This repo contains following utility class.
##### [Download the latest utils jar](https://github.com/maruthamuthu/utils/files/1184260/utils-1.0.jar.zip)
##### [Older version of utils jar](https://github.com/maruthamuthu/utils/issues/6)

## 1. HTTPS
      This package contains following classes.
         i.  HTTPParam
                This class is used to initialize the parameter.
         ii. HTTPProperties
                This class hold the all the values that need to invoke the call.
                Like URL, parameter, method and etc.
         iii.Connector
                This class is used to invoke the call based on the HTTPProperties.
## 2. Proprties
      The Core properties is a singleton class. It is used to initialize, set and get the property 
      across the java application.
## 3. String
      The StringUtil class is used to hold the string utility functions.
## 4. Exception
        i. CoreException
            This class is used to throw the error with error code, so that application can esily 
            determine the exception reason using error code.
        ii. ErrorConstants
             This class is used to define a error constants
