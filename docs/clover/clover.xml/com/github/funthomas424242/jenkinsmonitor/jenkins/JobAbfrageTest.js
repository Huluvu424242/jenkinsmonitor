var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":191,"id":1294,"methods":[{"el":66,"sc":5,"sl":54},{"el":76,"sc":5,"sl":69},{"el":81,"sc":5,"sl":78},{"el":96,"sc":5,"sl":83},{"el":110,"sc":5,"sl":98},{"el":124,"sc":5,"sl":112},{"el":138,"sc":5,"sl":126},{"el":152,"sc":5,"sl":141},{"el":163,"sc":5,"sl":154},{"el":176,"sc":5,"sl":165},{"el":188,"sc":5,"sl":178}],"name":"JobAbfrageTest","sl":42}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_13":{"methods":[{"sl":154}],"name":"getValidJsonGreen","pass":true,"statements":[{"sl":157},{"sl":158},{"sl":159},{"sl":160},{"sl":161},{"sl":162}]},"test_2":{"methods":[{"sl":178}],"name":"getValidJsonGray","pass":true,"statements":[{"sl":181},{"sl":182},{"sl":183},{"sl":184},{"sl":187}]},"test_23":{"methods":[{"sl":165}],"name":"getValidJsonYellow","pass":true,"statements":[{"sl":168},{"sl":169},{"sl":170},{"sl":171},{"sl":173},{"sl":174},{"sl":175}]},"test_28":{"methods":[{"sl":112}],"name":"getStatusYellow","pass":true,"statements":[{"sl":115},{"sl":116},{"sl":117},{"sl":118},{"sl":120},{"sl":121},{"sl":122},{"sl":123}]},"test_37":{"methods":[{"sl":141}],"name":"getValidJsonRed","pass":true,"statements":[{"sl":144},{"sl":145},{"sl":146},{"sl":147},{"sl":149},{"sl":150},{"sl":151}]},"test_43":{"methods":[{"sl":83}],"name":"getStatusRed","pass":true,"statements":[{"sl":87},{"sl":88},{"sl":89},{"sl":90},{"sl":92},{"sl":93},{"sl":94},{"sl":95}]},"test_5":{"methods":[{"sl":126}],"name":"getStatusGray","pass":true,"statements":[{"sl":129},{"sl":130},{"sl":131},{"sl":132},{"sl":134},{"sl":135},{"sl":136},{"sl":137}]},"test_8":{"methods":[{"sl":98}],"name":"getStatusGreen","pass":true,"statements":[{"sl":101},{"sl":102},{"sl":103},{"sl":104},{"sl":106},{"sl":107},{"sl":108},{"sl":109}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [43], [], [], [], [43], [43], [43], [43], [], [43], [43], [43], [43], [], [], [8], [], [], [8], [8], [8], [8], [], [8], [8], [8], [8], [], [], [28], [], [], [28], [28], [28], [28], [], [28], [28], [28], [28], [], [], [5], [], [], [5], [5], [5], [5], [], [5], [5], [5], [5], [], [], [], [37], [], [], [37], [37], [37], [37], [], [37], [37], [37], [], [], [13], [], [], [13], [13], [13], [13], [13], [13], [], [], [23], [], [], [23], [23], [23], [23], [], [23], [23], [23], [], [], [2], [], [], [2], [2], [2], [2], [], [], [2], [], [], [], []]
