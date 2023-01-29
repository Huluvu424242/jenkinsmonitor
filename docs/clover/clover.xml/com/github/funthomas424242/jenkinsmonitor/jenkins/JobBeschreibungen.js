var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":37,"id":808,"methods":[{"el":32,"sc":5,"sl":30},{"el":36,"sc":5,"sl":34}],"name":"JobBeschreibungen","sl":28}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_12":{"methods":[{"sl":30}],"name":"checkLadeStatusWithException","pass":true,"statements":[{"sl":31}]},"test_15":{"methods":[{"sl":34}],"name":"useUserNameFromConfigfile","pass":true,"statements":[{"sl":35}]},"test_17":{"methods":[{"sl":34}],"name":"reloadOtherConfiguration","pass":true,"statements":[{"sl":35}]},"test_2":{"methods":[{"sl":34}],"name":"useDefaultPollPeriod","pass":true,"statements":[{"sl":35}]},"test_21":{"methods":[{"sl":34}],"name":"validDefaultsWhenNotExistingConfigfile","pass":true,"statements":[{"sl":35}]},"test_23":{"methods":[{"sl":34}],"name":"reloadCurrentConfiguration","pass":true,"statements":[{"sl":35}]},"test_26":{"methods":[{"sl":34}],"name":"validDefaultsWithEmptyConfigfile","pass":true,"statements":[{"sl":35}]},"test_3":{"methods":[{"sl":34}],"name":"initAfterGetJobBeschreibungenOK","pass":true,"statements":[{"sl":35}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [12], [12], [], [], [26, 3, 17, 15, 21, 23, 2], [26, 3, 17, 15, 21, 23, 2], [], []]
