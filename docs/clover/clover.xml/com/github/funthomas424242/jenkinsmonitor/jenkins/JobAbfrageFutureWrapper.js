var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":44,"id":764,"methods":[{"el":34,"sc":5,"sl":31},{"el":38,"sc":5,"sl":36},{"el":42,"sc":5,"sl":40}],"name":"JobAbfrageFutureWrapper","sl":27}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_27":{"methods":[{"sl":31},{"sl":40}],"name":"checkLadeStatusWithException","pass":true,"statements":[{"sl":32},{"sl":33},{"sl":41}]},"test_6":{"methods":[{"sl":31},{"sl":40}],"name":"checkLadeOneJobStatusFailure","pass":true,"statements":[{"sl":32},{"sl":33},{"sl":41}]},"test_8":{"methods":[{"sl":31},{"sl":40}],"name":"checkLadeTwoJobStatusSUCCESSandUNSTABLE","pass":true,"statements":[{"sl":32},{"sl":33},{"sl":41}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [6, 27, 8], [6, 27, 8], [6, 27, 8], [], [], [], [], [], [], [6, 27, 8], [6, 27, 8], [], [], []]
