var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":64,"id":1073,"methods":[{"el":37,"sc":5,"sl":34},{"el":42,"sc":5,"sl":39},{"el":47,"sc":5,"sl":44},{"el":52,"sc":5,"sl":49},{"el":58,"sc":5,"sl":54},{"el":62,"sc":5,"sl":60}],"name":"TestDrivenTimer","sl":31}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_24":{"methods":[{"sl":34},{"sl":39},{"sl":49},{"sl":54},{"sl":60}],"name":"updateJobsAfterTimePeriod","pass":true,"statements":[{"sl":36},{"sl":57},{"sl":61}]},"test_28":{"methods":[{"sl":34},{"sl":39}],"name":"showStatusAsToolstippsIfJobsPresent","pass":true,"statements":[{"sl":36}]},"test_33":{"methods":[{"sl":34},{"sl":39}],"name":"showStatusAsToolstippsIfJobPresent","pass":true,"statements":[{"sl":36}]},"test_42":{"methods":[{"sl":34},{"sl":39}],"name":"shouldShowOneSuccessJobWatching","pass":true,"statements":[{"sl":36}]},"test_44":{"methods":[{"sl":34},{"sl":39}],"name":"shouldShowOneInstabilJobWatching","pass":true,"statements":[{"sl":36}]},"test_59":{"methods":[{"sl":34},{"sl":39}],"name":"shouldShowOneFailedJobWatching","pass":true,"statements":[{"sl":36}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [33, 42, 44, 59, 24, 28], [], [33, 42, 44, 59, 24, 28], [], [], [33, 42, 44, 59, 24, 28], [], [], [], [], [], [], [], [], [], [24], [], [], [], [], [24], [], [], [24], [], [], [24], [24], [], [], []]
