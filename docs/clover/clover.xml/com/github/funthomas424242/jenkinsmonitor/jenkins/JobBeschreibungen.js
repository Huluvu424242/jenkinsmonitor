var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":37,"id":798,"methods":[{"el":32,"sc":5,"sl":30},{"el":36,"sc":5,"sl":34}],"name":"JobBeschreibungen","sl":28}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_10":{"methods":[{"sl":34}],"name":"reloadCurrentConfiguration","pass":true,"statements":[{"sl":35}]},"test_13":{"methods":[{"sl":34}],"name":"useUserNameFromConfigfile","pass":true,"statements":[{"sl":35}]},"test_20":{"methods":[{"sl":34}],"name":"reloadOtherConfiguration","pass":true,"statements":[{"sl":35}]},"test_21":{"methods":[{"sl":30}],"name":"checkLadeOneJobStatusFailure","pass":true,"statements":[{"sl":31}]},"test_24":{"methods":[{"sl":34}],"name":"initAfterGetJobBeschreibungenOK","pass":true,"statements":[{"sl":35}]},"test_25":{"methods":[{"sl":30}],"name":"checkLadeStatusWithException","pass":true,"statements":[{"sl":31}]},"test_26":{"methods":[{"sl":34}],"name":"validDefaultsWithEmptyConfigfile","pass":true,"statements":[{"sl":35}]},"test_32":{"methods":[{"sl":34}],"name":"useDefaultPollPeriod","pass":true,"statements":[{"sl":35}]},"test_34":{"methods":[{"sl":30}],"name":"checkLadeTwoJobStatusSUCCESSandUNSTABLE","pass":true,"statements":[{"sl":31}]},"test_40":{"methods":[{"sl":34}],"name":"validDefaultsWhenNotExistingConfigfile","pass":true,"statements":[{"sl":35}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [34, 25, 21], [34, 25, 21], [], [], [24, 32, 40, 20, 13, 10, 26], [24, 32, 40, 20, 13, 10, 26], [], []]
