var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":89,"id":821,"methods":[{"el":47,"sc":5,"sl":42},{"el":52,"sc":5,"sl":49},{"el":56,"sc":5,"sl":54},{"el":61,"sc":5,"sl":58},{"el":65,"sc":5,"sl":63},{"el":70,"sc":5,"sl":67},{"el":82,"sc":5,"sl":73},{"el":87,"sc":5,"sl":84}],"name":"JobStatusBeschreibung","sl":29}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_10":{"methods":[{"sl":42},{"sl":54},{"sl":58},{"sl":63},{"sl":67}],"name":"initTwoJobsOneGreenOneRedIcon50x100","pass":true,"statements":[{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":55},{"sl":60},{"sl":64},{"sl":69}]},"test_12":{"methods":[{"sl":42},{"sl":54},{"sl":58},{"sl":63},{"sl":67}],"name":"checkLadeStatusWithException","pass":true,"statements":[{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":55},{"sl":60},{"sl":64},{"sl":69}]},"test_18":{"methods":[{"sl":42},{"sl":49}],"name":"instabileJobsSindGelb","pass":true,"statements":[{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":50},{"sl":51}]},"test_19":{"methods":[{"sl":42},{"sl":49}],"name":"erfolgreicheJobsSindGruen","pass":true,"statements":[{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":50},{"sl":51}]},"test_20":{"methods":[{"sl":42},{"sl":49},{"sl":54},{"sl":58}],"name":"valideInitialisierung","pass":true,"statements":[{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":50},{"sl":55},{"sl":60}]},"test_22":{"methods":[{"sl":42},{"sl":54},{"sl":58},{"sl":63},{"sl":67}],"name":"initOneJobRedIcon100x100","pass":true,"statements":[{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":55},{"sl":60},{"sl":64},{"sl":69}]},"test_24":{"methods":[{"sl":42},{"sl":54},{"sl":58},{"sl":63},{"sl":67}],"name":"initOneJobGreenIcon100x100","pass":true,"statements":[{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":55},{"sl":60},{"sl":64},{"sl":69}]},"test_4":{"methods":[{"sl":42},{"sl":54},{"sl":63}],"name":"getStatusGray","pass":true,"statements":[{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":55},{"sl":64}]},"test_5":{"methods":[{"sl":42},{"sl":49}],"name":"fehlerhafteJobsSindRot","pass":true,"statements":[{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":50},{"sl":51}]},"test_6":{"methods":[{"sl":42},{"sl":54},{"sl":58},{"sl":63},{"sl":67}],"name":"initOneJobYellowIcon100x100","pass":true,"statements":[{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":55},{"sl":60},{"sl":64},{"sl":69}]},"test_9":{"methods":[{"sl":73},{"sl":84}],"name":"checkEqualsAndHashCode","pass":true,"statements":[{"sl":75},{"sl":76},{"sl":77},{"sl":78},{"sl":86}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [5, 10, 18, 24, 20, 22, 19, 4, 12, 6], [5, 10, 18, 24, 20, 22, 19, 4, 12, 6], [5, 10, 18, 24, 20, 22, 19, 4, 12, 6], [5, 10, 18, 24, 20, 22, 19, 4, 12, 6], [5, 10, 18, 24, 20, 22, 19, 4, 12, 6], [], [], [5, 18, 20, 19], [5, 18, 20, 19], [5, 18, 19], [], [], [10, 24, 20, 22, 4, 12, 6], [10, 24, 20, 22, 4, 12, 6], [], [], [10, 24, 20, 22, 12, 6], [], [10, 24, 20, 22, 12, 6], [], [], [10, 24, 22, 4, 12, 6], [10, 24, 22, 4, 12, 6], [], [], [10, 24, 22, 12, 6], [], [10, 24, 22, 12, 6], [], [], [], [9], [], [9], [9], [9], [9], [], [], [], [], [], [9], [], [9], [], [], []]
