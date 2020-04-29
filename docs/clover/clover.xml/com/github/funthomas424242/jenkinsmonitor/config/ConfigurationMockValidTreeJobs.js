var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":42,"id":834,"methods":[{"el":35,"sc":5,"sl":33},{"el":40,"sc":5,"sl":37}],"name":"ConfigurationMockValidTreeJobs","sl":29}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_26":{"methods":[{"sl":33},{"sl":37}],"name":"showStatusAsToolstippsIfJobsPresent","pass":true,"statements":[{"sl":34},{"sl":38},{"sl":39}]},"test_33":{"methods":[{"sl":33},{"sl":37}],"name":"updateJobsAfterTimePeriod","pass":true,"statements":[{"sl":34},{"sl":38},{"sl":39}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [26, 33], [26, 33], [], [], [26, 33], [26, 33], [26, 33], [], [], []]
