# Lab 2

Test all things in repo by running 
`$ mvn test`
BSemIf might rarely fail giving back the correct value (0).

### ad. 2

Semaphore should fail, as we block just one time and we can easily get notify called on thread which should not get unlocked (variable which we first tested on did not change value). notify() might wake up any of waiting threads even if value remained unchanged, triggering condition racing.

### ad. 3

Counting semaphore should be seen as an extension to binary one, as the only difference between these two is blocking whenever we surpass certain value. Other than that, it supports exactly the same operations on same paradigm. We need another semaphore to ensure only one thread will get copy of resource at a time.