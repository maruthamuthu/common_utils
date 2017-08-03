# utils

### This repo contains following utility class.
##### [Download the latest utils jar](https://docs.zoho.com/file/3ory8cd41b6871dd04522b94692479bd50f14)
##### [Older version of utils jar](https://github.com/maruthamuthu/utils/wiki/Util-jar-Downloads)

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
        i. StringsUtil 
            This class is used to hold the string utility functions. Like isEmpty, isNotEmpty with null check.
        ii. StringAppender
             This is class used to addend the object, char into StringBuilder. It has a utility of append with traling space,
             lead space, etc.
## 4. Exception
        i. CoreException
            This class is used to throw the error with error code, so that application can esily 
            determine the exception reason using error code.
        ii. ErrorConstants
             This class is used to define a error constants
