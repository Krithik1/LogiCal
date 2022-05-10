package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//Represents a KarnaughMap
public class KarnaughMap {
    //Fields
    private final int[][] map;
    private final int numvariables;
    private final ArrayList<int[][]> boxes;
    private final ArrayList<int[][]> twosame;
    private static final Logic ONE = new Logic("1");
    private static final Logic ZERO = new Logic("0");
    private static final Logic A = new Logic("A");
    private static final Logic B = new Logic("B");
    private static final Logic C = new Logic("C");
    private static final Logic D = new Logic("D");
    private Logic ans = new Logic();

    // REQUIRES : numvariables != 0 && numvariables <= 4, input.length is in {2, 4, 8, 16}
    // MODIFIES : this
    // EFFECTS  : constructs a KarnaughMap
    public KarnaughMap(int numvariable, int[] input) {
        this.numvariables = numvariable;
        this.map = fnConstructor(numvariable, input);
        boxes = this.identifyBoxes();
        twosame = identifyTwoSame1(map);
    }

    // REQUIRES : map.length != 0 && map[i][j] = 0 or 1 && numvariables != 0 && numvariables <= 4
    // EFFECTS  : forms logic according to map
    //              returns logic in String form
    public String formLogic() {
        String ans = "";
        String answer = checkZeroOrOne();
        if (numvariables == 1) {
            ans =  formLogic1();
        } else if (numvariables == 2) {
            ans =  formLogic2();
        } else if (numvariables == 3) {
            ans = formLogic3(map);
        } else {
            ans =  formLogic4();
        }
        if (!answer.equals("")) {
            return answer;
        }
        if (ans.length() == 1 || ans.length() == 2) {
            return ans;
        }
        ans = ans.replace(" V ", ") V (");
        return "(" + ans + ")";
    }

    // REQUIRES : numvariable belongs to {1, 2, 3, 4}, input.length == 2 or 4 or 8 or 16
    // EFFECTS  : returns a Karnaugh Map according to the numvariables and input
    private int[][] fnConstructor(int numvariable, int[] input) {
        int[][] map;
        if (numvariable == 3) {
            map = new int[2][4];
        } else if (numvariable == 1) {
            map = new int[1][2];
        } else {
            map = new int[numvariable][numvariable];
        }
        if (input.length == 2) {
            map[0] = input;
        } else if (input.length == 4) {
            map[0] = Arrays.stream(input, 0, 2).toArray();
            map[1] = Arrays.stream(input, 2, 4).toArray();
        } else if (input.length == 8) {
            map[0] = Arrays.stream(input, 0, 4).toArray();
            map[1] = Arrays.stream(input, 4, 8).toArray();
        } else {
            map[0] = Arrays.stream(input, 0, 4).toArray();
            map[1] = Arrays.stream(input, 4, 8).toArray();
            map[2] = Arrays.stream(input, 8, 12).toArray();
            map[3] = Arrays.stream(input, 12, 16).toArray();
        }
        return map;
    }

    // REQUIRES : numvariables <= 4 && numvariables != 0
    // EFFECTS  : identifies coordinates in map if there are 4 1's forming a box
    private ArrayList<int[][]> identifyBoxes() {
        ArrayList<int[][]> x = new ArrayList<int[][]>();
        if (map.length == 1) {
            return x;
        } else {
            for (int i = 0; i < map.length - 1; i++) {
                for (int j = 0; j < map[0].length - 1; j++) {
                    if (map[i][j] == 1 && map[i][j + 1] == 1) {
                        if (map[i + 1][j] == 1 && map[i + 1][j + 1] == 1) {
                            int[][] b = {{i, j}, {i, j + 1}, {i + 1, j}, {i + 1, j + 1}};
                            x.add(b);
                        }
                    }
                }
            }
            return x;
        }
    }

    // REQUIRES : numvariables <= 4 && numvariables != 0
    // EFFECTS  : finds all pairs of ones adjacent to each other in map
    private static ArrayList<int[][]> identifyTwoSame1(int[][] map) {
        if (map.length == 1) {
            ArrayList<int[][]> anslst = new ArrayList<int[][]>();
            for (int i = 0; i < map[0].length - 1; i++) {
                if (map[0][i] == 1 && map[0][i + 1] == 1) {
                    int[][] ans = {{0, i}, {0, i + 1}};
                    anslst.add(ans);
                }
            }
            return anslst;
        } else {
            return identifyTwoSameMap234(map);
        }
    }

    // REQUIRES : numvariables > 1 && numvariables <= 4
    // EFFECTS  : finds all pairs of ones adjacent to each other in map where numvariables is not 1
    private static ArrayList<int[][]> identifyTwoSameMap234(int[][] map) {
        ArrayList<int[][]> anslst = new ArrayList<int[][]>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length - 1; j++) {
                if (map[i][j] == 1 && map[i][j + 1] == 1) {
                    int[][] ans = {{i, j}, {i, j + 1}};
                    anslst.add(ans);
                }
                if (i < map.length - 1 && map[i][j] == 1 && map[i + 1][j] == 1) {
                    int[][] ans = {{i, j}, {i + 1, j}};
                    anslst.add(ans);
                }
            }
        }
        return anslst;
    }

    // REQUIRES : numvariables == 1
    // MODIFIES : this.ans
    // EFFECTS  : returns a logic when numvariables == 1
    private String formLogic1() {
        if (map[0][0] == 0 && map[0][1] == 1) {
            ans = A;
        } else if (map[0][0] == 1 && map[0][1] == 0) {
            ans = new NOT(A);
        }
        return ans.toString();
    }

    // REQUIRES : numvariables == 2
    // MODIFIES : this.ans
    // EFFECTS  : returns a logic when numvariables == 1
    private String formLogic2() {
        int[][] mapcopy = map;
        ArrayList<int[][]> twosamecopy = twosame;
        if (twosame.size() > 0) {
            ans = twoSameLogic2();
            for (int i = 0; i < twosamecopy.size(); i++) {
                for (int j = 0; j < 2; j++) {
                    mapcopy[twosamecopy.get(i)[j][0]][twosamecopy.get(i)[j][1]] = 0;
                }
            }
        }
        ArrayList<Logic> orlst = new ArrayList<Logic>();
        if (!ans.toString().equals("")) {
            orlst.add(ans);
        }
        Logic individual = individualLogic2(mapcopy);
        if (!individual.toString().equals("")) {
            orlst.add(individual);
        }
        return (new OR(orlst)).toString();
    }

    // REQUIRES : numvariables == 2
    // MODIFIES : this.ans and mapcopy
    // EFFECTS  : returns a logic when numvariables == 1
    private String formLogic3(int[][] mapcopy) {
        ArrayList<Logic> orlst = new ArrayList<Logic>();
        if (boxes.size() > 0) {
            orlst.add(boxLogic3());
            for (int[][] i : boxes) {
                for (int[] j : i) {
                    mapcopy[j[0]][j[1]] = 0;
                }
            }
        }
        ArrayList<int[][]> twosamecopy = identifyTwoSame1(mapcopy);
        if (twosamecopy.size() > 0) {
            orlst.add(twoSameLogic3(twosamecopy));
            for (int i = 0; i < twosamecopy.size(); i++) {
                for (int j = 0; j < 2; j++) {
                    mapcopy[twosamecopy.get(i)[j][0]][twosamecopy.get(i)[j][1]] = 0;
                }
            }
        }
        orlst.addAll(individualLogic3(mapcopy));
        return (new OR(orlst)).toString();
    }

    // REQUIRES : numvariables == 2
    // MODIFIES : this.ans
    // EFFECTS  : returns a logic when numvariables == 1
    private String formLogic4() {
        int[][] mapcopy = map;
        ArrayList<Logic> orlst = new ArrayList<Logic>();
        if (boxes.size() > 0) {
            orlst.add(boxLogic4());
            for (int[][] i : boxes) {
                for (int[] j : i) {
                    mapcopy[j[0]][j[1]] = 0;
                }
            }
        }
        ArrayList<int[][]> twosamecopy = identifyTwoSame1(mapcopy);
        if (twosamecopy.size() > 0) {
            orlst.add(twoSameLogic4(twosamecopy));
            for (int i = 0; i < twosamecopy.size(); i++) {
                for (int j = 0; j < 2; j++) {
                    mapcopy[twosamecopy.get(i)[j][0]][twosamecopy.get(i)[j][1]] = 0;
                }
            }
        }
        orlst.addAll(edgeCases(mapcopy, new ArrayList<Logic>()));
        orlst.addAll(individualLogic4(mapcopy));
        ans = new OR(orlst);
        return ans.toString();
    }

    // REQUIRES : numvariables == 2
    // MODIFIES : mapcopy, andlst and orlst
    // EFFECTS  : checks for the edge cases when numvariable == 4
    private ArrayList<Logic> edgeCases(int[][] mapcopy, ArrayList<Logic> orlst) {
        Logic[] l1 = {new NOT(B), new NOT(C)};
        Logic[] l2 = {new NOT(B), D};
        Logic[] l3 = {new NOT(B), C};
        orlst.addAll(edgeCaseHelper(new ArrayList<Logic>(), l1, 0, 1, mapcopy));
        orlst.addAll(edgeCaseHelper(new ArrayList<Logic>(), l2, 1, 2, mapcopy));
        orlst.addAll(edgeCaseHelper(new ArrayList<Logic>(), l3, 2, 3, mapcopy));
        return orlst;
    }

    // MODIFIES : andlst, mapcopy
    // EFFECTS  : helper for edgeCases method
    private ArrayList<Logic> edgeCaseHelper(ArrayList<Logic> andlst, Logic[] l, int i, int j, int[][] mapcopy) {
        ArrayList<Logic> orlst = new ArrayList<Logic>();
        andlst.add(A);
        if (mapcopy[i][3] == 1 && mapcopy[j][3] == 1) {
            andlst.add(l[0]);
            andlst.add(l[1]);
            orlst.add(new AND(andlst));
            mapcopy[i][3] = 0;
            mapcopy[j][3] = 0;
        }
        return orlst;
    }

    // EFFECTS  : returns the logic when 2 one's are together; numvariables == 2
    private Logic twoSameLogic2() {
        ArrayList<Logic> orlist = new ArrayList<Logic>();
        for (int i = 0; i < twosame.size(); i++) {
            if (twosame.get(i)[0][0] == twosame.get(i)[1][0]) {
                if (twosame.get(i)[0][0] == 0) {
                    orlist.add(new NOT(B));
                } else {
                    orlist.add(B);
                }
            } else {
                orlist.add(new NOT(A));
            }
        }
        return new OR(orlist);
    }

    // EFFECTS  : returns the logic when 1 one is there; numvariables == 2
    private Logic individualLogic2(int[][] mapcopy) {
        ArrayList<Logic> orlist = new ArrayList<Logic>();
        if (mapcopy[0][0] == 1) {
            orlist.add(new AND(new NOT(B), new NOT(A)));
        }
        if (mapcopy[0][1] == 1) {
            orlist.add(new AND(new NOT(B), A));
        }
        if (mapcopy[1][0] == 1) {
            orlist.add(new AND(B, new NOT(A)));
        }
        if (mapcopy[1][1] == 1) {
            orlist.add(new AND(B, A));
        }
        return new OR(orlist);
    }

    // EFFECTS  : returns the logic when 4 one's occur in a box form; numvariables == 3
    private Logic boxLogic3() {
        ArrayList<Logic> ans1 = new ArrayList<Logic>();
        for (int[][] i : boxes) {
            if (i[0][1] == 0) {
                ans1.add(new NOT(A));
            } else if (i[0][1] == 1) {
                ans1.add(B);
            } else {
                ans1.add(A);
            }
        }
        return new OR(ans1);
    }

    // EFFECTS  : returns the logic when 2 one's are together; numvariables == 3
    private Logic twoSameLogic3(ArrayList<int[][]> twosamecopy) {
        ArrayList<Logic> orlist = new ArrayList<Logic>();
        for (int i = 0; i < twosamecopy.size(); i++) {
            if (twosamecopy.get(i)[0][0] == 0) {
                orlist = twoSameLogic3Helper(orlist, twosamecopy, i);
            } else {
                if (twosamecopy.get(i)[0][1] == 0) {
                    orlist.add((new AND(new NOT(A), C)));
                } else if (twosamecopy.get(i)[0][1] == 1) {
                    orlist.add((new AND(B, C)));
                } else {
                    orlist.add((new AND(A, C)));
                }
            }
        }
        return new OR(orlist);
    }

    // MODIFIES : orlist
    // EFFECTS  : Helper for twoSameLogic2
    private ArrayList<Logic> twoSameLogic3Helper(ArrayList<Logic> orlist, ArrayList<int[][]> twosamecopy, int i) {
        if (twosamecopy.get(i)[0][1] == 0) {
            if (twosamecopy.get(i)[1][0] == 0) {
                orlist.add((new AND(new NOT(A), new NOT(C))));
            } else {
                orlist.add((new AND(new NOT(A), new NOT(B))));
            }
        } else if (twosamecopy.get(i)[0][1] == 1) {
            if (twosamecopy.get(i)[1][0] == 0) {
                orlist.add((new AND(B, new NOT(C))));
            } else {
                orlist.add((new AND(new NOT(A), B)));
            }
        } else {
            if (twosamecopy.get(i)[1][0] == 0) {
                orlist.add((new AND(A, new NOT(C))));
            } else {
                orlist.add((new AND(A, B)));
            }
        }
        return orlist;
    }

    // EFFECTS  : returns the logic when 1 one is there; numvariables == 3
    private ArrayList<Logic> individualLogic3(int[][] mapcopy) {
        ArrayList<Logic> orlst = new ArrayList<Logic>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                ArrayList<Logic> andlst = new ArrayList<Logic>();
                Logic add1 = new Logic();
                Logic add2 = new Logic();
                Logic add3;
                int[] x = {i, j};
                if (i == 0) {
                    add3 = new NOT(C);
                    Logic[] l = {add1, add2, add3};
                    orlst = indvLog3Help(orlst, andlst, mapcopy, l, x);
                } else {
                    add3 = C;
                    Logic[] l = {add1, add2, add3};
                    orlst = indvLog3Help(orlst, andlst, mapcopy, l, x);
                }
            }
        }
        return orlst;
    }

    // REQUIRES : l.length == 3 && x.length == 2
    // MODIFIES : olst
    // EFFECTS  : Helper for indvLog3Help
    private ArrayList<Logic> indvLog3Help(ArrayList<Logic> olst, ArrayList<Logic> alst, int[][] m, Logic[] l, int[] x) {
        if (x[1] == 0 && m[x[0]][x[1]] == 1) {
            l[0] = new NOT(A);
            l[1] = new NOT(B);
            olst = indvLog3Help2(alst, olst, l);
        }
        if (x[1] == 1 && m[x[0]][x[1]] == 1) {
            l[0] = new NOT(A);
            l[1] = B;
            olst = indvLog3Help2(alst, olst, l);
        }
        if (x[1] == 2 && m[x[0]][x[1]] == 1) {
            l[0] = A;
            l[1] = B;
            olst = indvLog3Help2(alst, olst, l);
        }
        if (x[1] == 3 && m[x[0]][x[1]] == 1) {
            l[0] = A;
            l[1] = new NOT(B);
            olst = indvLog3Help2(alst, olst, l);
        }
        return olst;
    }

    // REQUIRES : l.length == 3
    // MODIFIES : alst, olst
    // EFFECTS  : Second Helper for individual logic 3
    private ArrayList<Logic> indvLog3Help2(ArrayList<Logic> alst, ArrayList<Logic> olst, Logic[] l) {
        alst.add(l[0]);
        alst.add(l[1]);
        alst.add(l[2]);
        olst.add(new AND(alst));
        return olst;
    }

    // EFFECTS  : returns the logic when 4 one's occur in a box form; numvariables == 4
    private Logic boxLogic4() {
        ArrayList<Logic> orlst = new ArrayList<Logic>();
        for (int[][] i : boxes) {
            if (i[0][0] == 0) {
                ArrayList<Logic> andlst = new ArrayList<Logic>();
                andlst = boxLogic4Helper(andlst,i);
                andlst.add(new NOT(C));
                orlst.add(new AND(andlst));
            } else if (i[0][0] == 1) {
                ArrayList<Logic> andlst = new ArrayList<Logic>();
                andlst = boxLogic4Helper(andlst,i);
                andlst.add(D);
                orlst.add(new AND(andlst));
            } else {
                ArrayList<Logic> andlst = new ArrayList<Logic>();
                andlst = boxLogic4Helper(andlst,i);
                andlst.add(C);
                orlst.add(new AND(andlst));
            }
        }
        return new OR(orlst);
    }

    // REQUIRES : box.length == 2, box[].length == 2
    // MODIFIES : andlst
    // EFFECTS  : Helper for boxLogic4
    private ArrayList<Logic> boxLogic4Helper(ArrayList<Logic> andlst, int[][] box) {
        if (box[0][1] == 0) {
            andlst.add(new NOT(A));
        } else if (box[0][1] == 1) {
            andlst.add(B);
        } else {
            andlst.add(A);
        }
        return andlst;
    }

    // EFFECTS  : returns the logic when 2 one's are together; numvariables == 2
    private Logic twoSameLogic4(ArrayList<int[][]> twosamecopy) {
        ArrayList<Logic> orlst = new ArrayList<Logic>();
        for (int[][] i : twosamecopy) {
            orlst.add(twoSameLogic4Helper(i, i[0][0]));
        }
        return new OR(orlst);
    }

    // EFFECTS  : Helper for twoSameLogic4
    private Logic twoSameLogic4Helper(int[][] twosameelement, int j) {
        ArrayList<Logic> andlst = new ArrayList<Logic>();
        ArrayList<Logic> orlst = new ArrayList<Logic>();
        ArrayList<ArrayList<Logic>> lst = new ArrayList<ArrayList<Logic>>();
        lst.add(andlst);
        lst.add(orlst);
        if (j == 0) {
            Logic[] l1 = {new NOT(C), new NOT(D), new NOT(A), new NOT(C), new NOT(D), B, new NOT(C), new NOT(D), A};
            Logic[] l2 = {new NOT(A), new NOT(B), new NOT(C), new NOT(A), B, new NOT(C), A, new NOT(B), new NOT(C)};
            return twoSameLogic4Help2(l1, l2, twosameelement, lst, 0);
        } else if (j == 1) {
            Logic[] l1 = {new NOT(C), D, new NOT(A), new NOT(C), D, B, new NOT(C), D, A};
            Logic[] l2 = {new NOT(A), new NOT(B), D, new NOT(A), B, D, A, new NOT(B), D};
            return twoSameLogic4Help2(l1, l2, twosameelement, lst, 1);
        } else if (j == 2) {
            Logic[] l1 = {C, D, new NOT(A), C, D, B, C, D, A};
            Logic[] l2 = {new NOT(A), new NOT(B), C, new NOT(A), B, C, A, new NOT(B), C};
            return twoSameLogic4Help2(l1, l2, twosameelement, lst, 2);
        } else {
            Logic[] l1 = {C, new NOT(D), new NOT(A), C, new NOT(D), B, C, new NOT(D), A};
            Logic[] l2 = {new NOT(A), new NOT(B), new NOT(C), new NOT(A), B, new NOT(C), A, new NOT(B), C};
            return twoSameLogic4Help2(l1, l2, twosameelement, lst, 3);
        }
    }

    // REQUIRES : l1.length = 9, lst.size() = 2
    // EFFECTS  : Second helper for twoSameLogic4
    private Logic twoSameLogic4Help2(Logic[] l1, Logic[] l2, int[][] element, ArrayList<ArrayList<Logic>> lst, int j) {
        if (element[1][0] == j) {
            return twoSameLogic4Help3(l1,element, lst);
        } else {
            return twoSameLogic4Help3(l2,element, lst);
        }
    }

    // REQUIRES : l.length = 9, lst.size() = 2
    // MODIFIES : lst
    // EFFECTS  : Third helper for twoSameLogic4
    private Logic twoSameLogic4Help3(Logic[] l, int[][] twosameelement, ArrayList<ArrayList<Logic>> lst) {
        if (twosameelement[0][1] == 0) {
            lst.get(0).add(l[0]);
            lst.get(0).add(l[1]);
            lst.get(0).add(l[2]);
            lst.get(1).add(new AND(lst.get(0)));
        } else if (twosameelement[0][1] == 1) {
            lst.get(0).add(l[3]);
            lst.get(0).add(l[4]);
            lst.get(0).add(l[5]);
            lst.get(1).add(new AND(lst.get(0)));
        } else {
            lst.get(0).add(l[6]);
            lst.get(0).add(l[7]);
            lst.get(0).add(l[8]);
            lst.get(1).add(new AND(lst.get(0)));
        }
        return new OR(lst.get(1));
    }

    // EFFECTS  : returns the logic when 1 one is there; numvariables == 4
    private ArrayList<Logic> individualLogic4(int[][] mapcopy) {
        ArrayList<Logic> orlst = new ArrayList<Logic>();
        orlst.addAll(individualLogic41(mapcopy));
        orlst.addAll(individualLogic42(mapcopy));
        orlst.addAll(individualLogic43(mapcopy));
        orlst.addAll(individualLogic44(mapcopy));
        return orlst;
    }

    // EFFECTS  : returns logic when 1 one is there for row 1; numvariables == 4
    private ArrayList<Logic> individualLogic41(int[][] mapcopy) {
        ArrayList<Logic> orlst = new ArrayList<Logic>();
        if (mapcopy[0][0] == 1) {
            Logic[] andlst1 = {new NOT(C), new NOT(D), new NOT(A), new NOT(B)};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        if (mapcopy[0][1] == 1) {
            Logic[] andlst1 = {new NOT(C), D, new NOT(A), new NOT(B)};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        if (mapcopy[0][2] == 1) {
            Logic[] andlst1 = {C, D, new NOT(A), new NOT(B)};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        if (mapcopy[0][3] == 1) {
            Logic[] andlst1 = {C, new NOT(D), new NOT(A), new NOT(B)};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        return orlst;
    }

    // EFFECTS  : returns logic when 1 one is there for row 2; numvariables == 4
    private ArrayList<Logic> individualLogic42(int[][] mapcopy) {
        ArrayList<Logic> orlst = new ArrayList<Logic>();
        if (mapcopy[1][0] == 1) {
            Logic[] andlst1 = {new NOT(C), new NOT(D), new NOT(A), B};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        if (mapcopy[1][1] == 1) {
            Logic[] andlst1 = {new NOT(C), D, new NOT(A), B};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        if (mapcopy[1][2] == 1) {
            Logic[] andlst1 = {C, D, new NOT(A), B};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        if (mapcopy[1][3] == 1) {
            Logic[] andlst1 = {C, new NOT(D), new NOT(A), B};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        return orlst;
    }

    // EFFECTS  : returns logic when 1 one is there for row 3; numvariables == 4
    private ArrayList<Logic> individualLogic43(int[][] mapcopy) {
        ArrayList<Logic> orlst = new ArrayList<Logic>();
        if (mapcopy[2][0] == 1) {
            Logic[] andlst1 = {new NOT(C), new NOT(D), A, B};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        if (mapcopy[2][1] == 1) {
            Logic[] andlst1 = {new NOT(C), D, A, B};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        if (mapcopy[2][2] == 1) {
            Logic[] andlst1 = {C, D, A, B};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        if (mapcopy[2][3] == 1) {
            Logic[] andlst1 = {C, new NOT(D), A, B};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        return orlst;
    }

    // EFFECTS  : returns logic when 1 one is there for row 4; numvariables == 4
    private ArrayList<Logic> individualLogic44(int[][] mapcopy) {
        ArrayList<Logic> orlst = new ArrayList<Logic>();
        if (mapcopy[3][0] == 1) {
            Logic[] andlst1 = {new NOT(C), new NOT(D), A, new NOT(B)};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        if (mapcopy[3][1] == 1) {
            Logic[] andlst1 = {new NOT(C), D, A, new NOT(B)};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        if (mapcopy[3][2] == 1) {
            Logic[] andlst1 = {C, D, A, new NOT(B)};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        if (mapcopy[3][3] == 1) {
            Logic[] andlst1 = {C, new NOT(D), A, new NOT(B)};
            orlst.addAll(individualLogic4Helper(andlst1, new ArrayList<Logic>()));
        }
        return orlst;
    }

    // EFFECTS  : Helper for individual logic 4
    private ArrayList<Logic> individualLogic4Helper(Logic[] l, ArrayList<Logic> andlst) {
        ArrayList<Logic> orlst = new ArrayList<Logic>();
        Collections.addAll(andlst, l);
        orlst.add(new AND(andlst));
        return orlst;
    }

    // REQUIRES : map.length != 0
    // EFFECTS  : checks if all the entries in map are 0 or 1
    private String checkZeroOrOne() {
        int onecount = 0;
        int zerocount = 0;
        for (int[] i : map) {
            for (int j : i) {
                if (j == 0) {
                    zerocount++;
                } else {
                    onecount++;
                }
            }
        }
        if (onecount == Math.pow(2, numvariables)) {
            return ONE.toString();
        }
        if (zerocount == Math.pow(2, numvariables)) {
            return ZERO.toString();
        }
        return "";
    }
}
