var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":218,"id":20,"methods":[{"el":85,"sc":5,"sl":73},{"el":89,"sc":5,"sl":87},{"el":95,"sc":5,"sl":92},{"el":105,"sc":5,"sl":103},{"el":110,"sc":5,"sl":108},{"el":120,"sc":5,"sl":112},{"el":129,"sc":5,"sl":122},{"el":134,"sc":5,"sl":132},{"el":140,"sc":5,"sl":136},{"el":154,"sc":5,"sl":142},{"el":158,"sc":5,"sl":156},{"el":164,"sc":5,"sl":160},{"el":193,"sc":5,"sl":166},{"el":216,"sc":5,"sl":195}],"name":"Configuration","sl":53}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_0":{"methods":[{"sl":112},{"sl":122},{"sl":132},{"sl":136},{"sl":142},{"sl":160},{"sl":195}],"name":"useDefaultPollPeriod","pass":true,"statements":[{"sl":113},{"sl":114},{"sl":119},{"sl":123},{"sl":124},{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":147},{"sl":148},{"sl":152},{"sl":153},{"sl":161},{"sl":162},{"sl":163},{"sl":196},{"sl":202},{"sl":203},{"sl":204},{"sl":205},{"sl":206},{"sl":207},{"sl":208},{"sl":209},{"sl":215}]},"test_10":{"methods":[{"sl":132},{"sl":136},{"sl":142},{"sl":160},{"sl":195}],"name":"validDefaultsWithEmptyConfigfile","pass":true,"statements":[{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":147},{"sl":148},{"sl":152},{"sl":153},{"sl":161},{"sl":162},{"sl":163},{"sl":196},{"sl":215}]},"test_11":{"methods":[{"sl":132},{"sl":136},{"sl":142},{"sl":160},{"sl":195}],"name":"validDefaultsWhenNotExistingConfigfile","pass":true,"statements":[{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":150},{"sl":152},{"sl":153},{"sl":161},{"sl":162},{"sl":163},{"sl":196},{"sl":215}]},"test_13":{"methods":[{"sl":112},{"sl":122},{"sl":132},{"sl":136},{"sl":142},{"sl":160},{"sl":195}],"name":"reloadCurrentConfiguration","pass":true,"statements":[{"sl":113},{"sl":114},{"sl":119},{"sl":123},{"sl":124},{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":147},{"sl":148},{"sl":152},{"sl":153},{"sl":161},{"sl":162},{"sl":163},{"sl":196},{"sl":202},{"sl":203},{"sl":204},{"sl":205},{"sl":206},{"sl":207},{"sl":208},{"sl":209},{"sl":215}]},"test_14":{"methods":[{"sl":87},{"sl":92},{"sl":132},{"sl":136},{"sl":142},{"sl":156},{"sl":160}],"name":"initAfterGetPollOK","pass":true,"statements":[{"sl":88},{"sl":93},{"sl":94},{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":147},{"sl":148},{"sl":152},{"sl":153},{"sl":157},{"sl":161},{"sl":162},{"sl":163}]},"test_18":{"methods":[{"sl":87},{"sl":92},{"sl":132},{"sl":136},{"sl":142},{"sl":156},{"sl":195}],"name":"initAfterGetJobBeschreibungenOK","pass":true,"statements":[{"sl":88},{"sl":93},{"sl":94},{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":147},{"sl":148},{"sl":152},{"sl":153},{"sl":157},{"sl":196},{"sl":215}]},"test_19":{"methods":[{"sl":112},{"sl":122},{"sl":132},{"sl":136},{"sl":142},{"sl":195}],"name":"useUserNameFromConfigfile","pass":true,"statements":[{"sl":113},{"sl":114},{"sl":119},{"sl":123},{"sl":124},{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":147},{"sl":148},{"sl":152},{"sl":153},{"sl":196},{"sl":202},{"sl":203},{"sl":204},{"sl":205},{"sl":206},{"sl":207},{"sl":208},{"sl":209},{"sl":215}]},"test_23":{"methods":[{"sl":73},{"sl":87},{"sl":92},{"sl":103},{"sl":108}],"name":"useDefaultConfigfile","pass":true,"statements":[{"sl":74},{"sl":75},{"sl":76},{"sl":88},{"sl":93},{"sl":94},{"sl":104},{"sl":109}]},"test_6":{"methods":[{"sl":73},{"sl":87},{"sl":92},{"sl":103},{"sl":108}],"name":"useSharedDefaultConfigfile","pass":true,"statements":[{"sl":74},{"sl":75},{"sl":78},{"sl":79},{"sl":82},{"sl":88},{"sl":93},{"sl":94},{"sl":104},{"sl":109}]},"test_9":{"methods":[{"sl":87},{"sl":92},{"sl":108},{"sl":112},{"sl":122},{"sl":132},{"sl":136},{"sl":142},{"sl":195}],"name":"reloadOtherConfiguration","pass":true,"statements":[{"sl":88},{"sl":93},{"sl":94},{"sl":109},{"sl":113},{"sl":114},{"sl":119},{"sl":123},{"sl":124},{"sl":133},{"sl":137},{"sl":138},{"sl":139},{"sl":143},{"sl":144},{"sl":145},{"sl":146},{"sl":147},{"sl":148},{"sl":152},{"sl":153},{"sl":196},{"sl":202},{"sl":203},{"sl":204},{"sl":205},{"sl":206},{"sl":207},{"sl":208},{"sl":209},{"sl":215}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [6, 23], [6, 23], [6, 23], [23], [], [6], [6], [], [], [6], [], [], [], [], [9, 14, 18, 6, 23], [9, 14, 18, 6, 23], [], [], [], [9, 14, 18, 6, 23], [9, 14, 18, 6, 23], [9, 14, 18, 6, 23], [], [], [], [], [], [], [], [], [6, 23], [6, 23], [], [], [], [9, 6, 23], [9, 6, 23], [], [], [0, 13, 9, 19], [0, 13, 9, 19], [0, 13, 9, 19], [], [], [], [], [0, 13, 9, 19], [], [], [0, 13, 9, 19], [0, 13, 9, 19], [0, 13, 9, 19], [], [], [], [], [], [], [], [0, 10, 13, 9, 19, 14, 18, 11], [0, 10, 13, 9, 19, 14, 18, 11], [], [], [0, 10, 13, 9, 19, 14, 18, 11], [0, 10, 13, 9, 19, 14, 18, 11], [0, 10, 13, 9, 19, 14, 18, 11], [0, 10, 13, 9, 19, 14, 18, 11], [], [], [0, 10, 13, 9, 19, 14, 18, 11], [0, 10, 13, 9, 19, 14, 18, 11], [0, 10, 13, 9, 19, 14, 18, 11], [0, 10, 13, 9, 19, 14, 18, 11], [0, 10, 13, 9, 19, 14, 18, 11], [0, 10, 13, 9, 19, 14, 18], [0, 10, 13, 9, 19, 14, 18], [], [11], [], [0, 10, 13, 9, 19, 14, 18, 11], [0, 10, 13, 9, 19, 14, 18, 11], [], [], [14, 18], [14, 18], [], [], [0, 10, 13, 14, 11], [0, 10, 13, 14, 11], [0, 10, 13, 14, 11], [0, 10, 13, 14, 11], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [0, 10, 13, 9, 19, 18, 11], [0, 10, 13, 9, 19, 18, 11], [], [], [], [], [], [0, 13, 9, 19], [0, 13, 9, 19], [0, 13, 9, 19], [0, 13, 9, 19], [0, 13, 9, 19], [0, 13, 9, 19], [0, 13, 9, 19], [0, 13, 9, 19], [], [], [], [], [], [0, 10, 13, 9, 19, 18, 11], [], [], []]
