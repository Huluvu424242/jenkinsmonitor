var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":44,"id":767,"methods":[{"el":34,"sc":5,"sl":31},{"el":38,"sc":5,"sl":36},{"el":42,"sc":5,"sl":40}],"name":"JobAbfrageFutureWrapper","sl":27}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_14":{"methods":[{"sl":31},{"sl":40}],"name":"checkLadeStatusWithException","pass":true,"statements":[{"sl":32},{"sl":33},{"sl":41}]},"test_16":{"methods":[{"sl":31},{"sl":40}],"name":"checkLadeOneJobStatusFailure","pass":true,"statements":[{"sl":32},{"sl":33},{"sl":41}]},"test_38":{"methods":[{"sl":31},{"sl":40}],"name":"checkLadeTwoJobStatusSUCCESSandUNSTABLE","pass":true,"statements":[{"sl":32},{"sl":33},{"sl":41}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [38, 14, 16], [38, 14, 16], [38, 14, 16], [], [], [], [], [], [], [38, 14, 16], [38, 14, 16], [], [], []]
