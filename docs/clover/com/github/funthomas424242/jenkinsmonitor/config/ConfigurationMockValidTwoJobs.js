var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":42,"id":790,"methods":[{"el":35,"sc":5,"sl":33},{"el":40,"sc":5,"sl":37}],"name":"ConfigurationMockValidTwoJobs","sl":29}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_21":{"methods":[{"sl":33},{"sl":37}],"name":"reloadOtherConfiguration","pass":true,"statements":[{"sl":34},{"sl":38},{"sl":39}]},"test_35":{"methods":[{"sl":33},{"sl":37}],"name":"afterInitTrayIconExists","pass":true,"statements":[{"sl":34},{"sl":38},{"sl":39}]},"test_44":{"methods":[{"sl":33},{"sl":37}],"name":"equalsConfigWithTrayAndMonitor","pass":true,"statements":[{"sl":34},{"sl":38},{"sl":39}]},"test_47":{"methods":[{"sl":33},{"sl":37}],"name":"showStatusAsToolstippsIfJobPresent","pass":true,"statements":[{"sl":34},{"sl":38},{"sl":39}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [44, 35, 21, 47], [44, 35, 21, 47], [], [], [44, 35, 21, 47], [44, 35, 21, 47], [44, 35, 21, 47], [], [], []]
