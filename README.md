# jcalc2023
 A calculator utility written in java. Updated build with jar file for 2023.
 Link to original: https://github.com/BaconEggsRL/jcalc2016

jcalc is a calculator written in Java. This project began as a school assignment. The plan was originally for a text-based interpreter of basic operations, but it has since evolved a visual display and common functions. I created this repository to explore and share improvements to the calculator, and as a means for version control. Future goals for the project include: -Implied multiplication, eg. 8(3)=24 and with constants, 8(pi) -Implied change to previous answer, eg. +7=ans+7 -Improved functionality of variables, eg. use in functions -Throw user-friendly errors (IllegalArgumentException-->there was a problem with your input) -Development of various views (button view) -Ability to graph equations, graph(y=4x+3) etc


## Instructions for local debugging:
This .jar file is ran in the browser using cheerpj:
https://labs.leaningtech.com/cheerpj3

cheerpj will only run locally on a web server (cannot just double click index.html and have it work.)
So follow the steps below to set up a local web server:

Install node.js and npm (npx), then verify installation through the command line:
node -v
npm -v

Open a local html server in same folder as your index.html (and .jar file):
npx http-server -p 8080

Then go to
http://localhost:8080

And the .jar file should load (may take a while as this uses a free version of cheerpj.)
