var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":224,"id":20,"methods":[{"el":85,"sc":5,"sl":73},{"el":89,"sc":5,"sl":87},{"el":95,"sc":5,"sl":92},{"el":105,"sc":5,"sl":103},{"el":110,"sc":5,"sl":108},{"el":120,"sc":5,"sl":112},{"el":129,"sc":5,"sl":122},{"el":134,"sc":5,"sl":132},{"el":140,"sc":5,"sl":136},{"el":154,"sc":5,"sl":142},{"el":158,"sc":5,"sl":156},{"el":164,"sc":5,"sl":160},{"el":199,"sc":5,"sl":166},{"el":222,"sc":5,"sl":201}],"name":"Configuration","sl":53}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_12":{"methods":[{"sl":112},{"sl":122},{"sl":132},{"sl":136},{"sl":142},{"sl":160},{"sl":201}],"name":"reloadCurrentConfiguration","pass":true,"statements":[{"sl":113},{"sl":114},{"sl":119},{"sl":123},{"sl":124},{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":147},{"sl":148},{"sl":152},{"sl":153},{"sl":161},{"sl":162},{"sl":163},{"sl":202},{"sl":208},{"sl":209},{"sl":210},{"sl":211},{"sl":212},{"sl":213},{"sl":214},{"sl":215},{"sl":221}]},"test_19":{"methods":[{"sl":112},{"sl":122},{"sl":132},{"sl":136},{"sl":142},{"sl":160},{"sl":201}],"name":"useDefaultPollPeriod","pass":true,"statements":[{"sl":113},{"sl":114},{"sl":119},{"sl":123},{"sl":124},{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":147},{"sl":148},{"sl":152},{"sl":153},{"sl":161},{"sl":162},{"sl":163},{"sl":202},{"sl":208},{"sl":209},{"sl":210},{"sl":211},{"sl":212},{"sl":213},{"sl":214},{"sl":215},{"sl":221}]},"test_25":{"methods":[{"sl":132},{"sl":136},{"sl":142},{"sl":160},{"sl":201}],"name":"validDefaultsWithEmptyConfigfile","pass":true,"statements":[{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":147},{"sl":148},{"sl":152},{"sl":153},{"sl":161},{"sl":162},{"sl":163},{"sl":202},{"sl":221}]},"test_26":{"methods":[{"sl":132},{"sl":136},{"sl":142},{"sl":160},{"sl":201}],"name":"validDefaultsWhenNotExistingConfigfile","pass":true,"statements":[{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":150},{"sl":152},{"sl":153},{"sl":161},{"sl":162},{"sl":163},{"sl":202},{"sl":221}]},"test_31":{"methods":[{"sl":112},{"sl":122},{"sl":132},{"sl":136},{"sl":142},{"sl":201}],"name":"useUserNameFromConfigfile","pass":true,"statements":[{"sl":113},{"sl":114},{"sl":119},{"sl":123},{"sl":124},{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":147},{"sl":148},{"sl":152},{"sl":153},{"sl":202},{"sl":208},{"sl":209},{"sl":210},{"sl":211},{"sl":212},{"sl":213},{"sl":214},{"sl":215},{"sl":221}]},"test_4":{"methods":[{"sl":87},{"sl":92},{"sl":132},{"sl":136},{"sl":142},{"sl":156},{"sl":201}],"name":"initAfterGetJobBeschreibungenOK","pass":true,"statements":[{"sl":88},{"sl":93},{"sl":94},{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":147},{"sl":148},{"sl":152},{"sl":153},{"sl":157},{"sl":202},{"sl":221}]},"test_5":{"methods":[{"sl":73},{"sl":87},{"sl":92},{"sl":103},{"sl":108}],"name":"useDefaultConfigfile","pass":true,"statements":[{"sl":74},{"sl":75},{"sl":76},{"sl":88},{"sl":93},{"sl":94},{"sl":104},{"sl":109}]},"test_6":{"methods":[{"sl":87},{"sl":92},{"sl":108},{"sl":112},{"sl":122},{"sl":132},{"sl":136},{"sl":142},{"sl":201}],"name":"reloadOtherConfiguration","pass":true,"statements":[{"sl":88},{"sl":93},{"sl":94},{"sl":109},{"sl":113},{"sl":114},{"sl":119},{"sl":123},{"sl":124},{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":147},{"sl":148},{"sl":152},{"sl":153},{"sl":202},{"sl":208},{"sl":209},{"sl":210},{"sl":211},{"sl":212},{"sl":213},{"sl":214},{"sl":215},{"sl":221}]},"test_8":{"methods":[{"sl":87},{"sl":92},{"sl":132},{"sl":136},{"sl":142},{"sl":156},{"sl":160}],"name":"initAfterGetPollOK","pass":true,"statements":[{"sl":88},{"sl":93},{"sl":94},{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":147},{"sl":148},{"sl":152},{"sl":153},{"sl":157},{"sl":161},{"sl":162},{"sl":163}]},"test_9":{"methods":[{"sl":73},{"sl":87},{"sl":92},{"sl":103},{"sl":108}],"name":"useSharedDefaultConfigfile","pass":true,"statements":[{"sl":74},{"sl":75},{"sl":78},{"sl":79},{"sl":82},{"sl":88},{"sl":93},{"sl":94},{"sl":104},{"sl":109}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [5, 9], [5, 9], [5, 9], [5], [], [9], [9], [], [], [9], [], [], [], [], [8, 4, 6, 5, 9], [8, 4, 6, 5, 9], [], [], [], [8, 4, 6, 5, 9], [8, 4, 6, 5, 9], [8, 4, 6, 5, 9], [], [], [], [], [], [], [], [], [5, 9], [5, 9], [], [], [], [6, 5, 9], [6, 5, 9], [], [], [31, 6, 12, 19], [31, 6, 12, 19], [31, 6, 12, 19], [], [], [], [], [31, 6, 12, 19], [], [], [31, 6, 12, 19], [31, 6, 12, 19], [31, 6, 12, 19], [], [], [], [], [], [], [], [31, 8, 4, 6, 26, 12, 19, 25], [31, 8, 4, 6, 26, 12, 19, 25], [], [], [31, 8, 4, 6, 26, 12, 19, 25], [31, 8, 4, 6, 26, 12, 19, 25], [31, 8, 4, 6, 26, 12, 19, 25], [31, 8, 4, 6, 26, 12, 19, 25], [], [], [31, 8, 4, 6, 26, 12, 19, 25], [31, 8, 4, 6, 26, 12, 19, 25], [31, 8, 4, 6, 26, 12, 19, 25], [31, 8, 4, 6, 26, 12, 19, 25], [31, 8, 4, 6, 26, 12, 19, 25], [31, 8, 4, 6, 12, 19, 25], [31, 8, 4, 6, 12, 19, 25], [], [26], [], [31, 8, 4, 6, 26, 12, 19, 25], [31, 8, 4, 6, 26, 12, 19, 25], [], [], [8, 4], [8, 4], [], [], [8, 26, 12, 19, 25], [8, 26, 12, 19, 25], [8, 26, 12, 19, 25], [8, 26, 12, 19, 25], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [31, 4, 6, 26, 12, 19, 25], [31, 4, 6, 26, 12, 19, 25], [], [], [], [], [], [31, 6, 12, 19], [31, 6, 12, 19], [31, 6, 12, 19], [31, 6, 12, 19], [31, 6, 12, 19], [31, 6, 12, 19], [31, 6, 12, 19], [31, 6, 12, 19], [], [], [], [], [], [31, 4, 6, 26, 12, 19, 25], [], [], []]
