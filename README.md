# apiTestFramework

**How to run a test**

To run a test through the command line:
1. Navigate to where the repo is stored in your terminal
2. Enter mvn test -Dgroups="[tagname]"
3. It will then run the test of the specified tag with default properties. The tag names can be found at the top of a feature file. Make sure to enter the tag name without the @.
4. A report will be generated within apiTestFramework\target\cucumber-reports\

Example command: mvn test -Dgroups="scenarios"
- This will run all the tests written within the features folder.