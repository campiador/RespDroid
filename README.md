RespDroid

Static/Dynamic Analysis Tool for Responsiveness Measurement Across Multiple Hardwares

RespDroid (1) models, (2) measures, and (3) predicts peformance bugs.

1. Model
  - Every delay of over 100 milliseconds on the main thread (aka UI thread) of an app is modelled as a soft performance bug.
  - Every delay of over 200 milliseconds on the main thread (aka UI thread) of an app is modelled as a soft performance bug.
  - Every window transition taking over 200 millisecond is a performance bug.
  - Every intra-activity animation running below 50 fps is a performance bug.
  - ...

2. Measure
  - RespDroid uses Soot to instrument source or binary code at susceptible regions and APIs
  - RespDroid runs the instrumented app
  - The app leaves trace
  - RespDroid collects traces and detects the performance bugs

3. Predict
  - ResDroid predics the performance of an app on devices the user does not own using machine learning:
  - RespDroid uses performance scores from multiple 'training' devices to build a machine learning predictor
  - The predictor uses Support Vector Machines (SVM)
  - The predictor currently has a precision of 85%, when using app scores on Nexus 4 and Nexus 5X to predict scores on Nexus 6P 
