var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":42,"id":765,"methods":[{"el":35,"sc":5,"sl":33},{"el":40,"sc":5,"sl":37}],"name":"ConfigurationMockNoExisting","sl":29}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_46":{"methods":[{"sl":33},{"sl":37}],"name":"checkTrayInstanz","pass":true,"statements":[{"sl":34},{"sl":38},{"sl":39}]},"test_48":{"methods":[{"sl":33},{"sl":37}],"name":"initialConfigWithEmptyJobs","pass":true,"statements":[{"sl":34},{"sl":38},{"sl":39}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [46, 48], [46, 48], [], [], [46, 48], [46, 48], [46, 48], [], [], []]
