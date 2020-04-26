var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":42,"id":789,"methods":[{"el":35,"sc":5,"sl":33},{"el":40,"sc":5,"sl":37}],"name":"ConfigurationMockValidTwoJobs","sl":29}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_22":{"methods":[{"sl":33},{"sl":37}],"name":"equalsConfigWithTrayAndMonitor","pass":true,"statements":[{"sl":34},{"sl":38},{"sl":39}]},"test_23":{"methods":[{"sl":33},{"sl":37}],"name":"reloadOtherConfiguration","pass":true,"statements":[{"sl":34},{"sl":38},{"sl":39}]},"test_33":{"methods":[{"sl":33},{"sl":37}],"name":"afterInitTrayIconExists","pass":true,"statements":[{"sl":34},{"sl":38},{"sl":39}]},"test_7":{"methods":[{"sl":33},{"sl":37}],"name":"showStatusAsToolstippsIfJobPresent","pass":true,"statements":[{"sl":34},{"sl":38},{"sl":39}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [33, 7, 22, 23], [33, 7, 22, 23], [], [], [33, 7, 22, 23], [33, 7, 22, 23], [33, 7, 22, 23], [], [], []]
