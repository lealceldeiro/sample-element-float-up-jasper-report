# sample-element-float-up-jasper-report
Demo project for showing up how a jasper element floats up when its `positionType="Float"` and it is located below an element with `isRemoveLineWhenBlank="true"` and its `printWhenExpression` property evaluates to `false`.

The master branch contains a flaw described here: [How to organize Frames in Jasper Report so they float flawlessly?][2]. A solution to this problem is in the [fix-frame branch][3] (with this [commit][4])

## Run

(Local [Maven][1] distribution)
From project directory run the following command: `mvn spring-boot:run`

[1]: https://maven.apache.org/install.html
[2]]: https://stackoverflow.com/q/52654844/5640649
[3]: https://github.com/lealceldeiro/sample-element-float-up-jasper-report/tree/fix-frame
[4]: https://github.com/lealceldeiro/sample-element-float-up-jasper-report/commit/186383228500dc678539f0c2a51b8618787a92cc
