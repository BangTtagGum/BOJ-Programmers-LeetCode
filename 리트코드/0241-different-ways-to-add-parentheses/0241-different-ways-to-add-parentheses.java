class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> result = getCalculateResult(0, expression.length() - 1, expression);

        return result;
    }

    public List<Integer> getCalculateResult(int left, int right, String expression) {
        List<Integer> result = new ArrayList<>();
        String substring = expression.substring(left, right + 1);

        if (!substring.contains("*") && !substring.contains("-") && !substring.contains("+")) {
            result.add(Integer.parseInt(substring));
            return result;
        }

        for (int i = left; i <= right; i++) {
            if (!Character.isDigit(expression.charAt(i))) {
                List<Integer> leftList = getCalculateResult(left, i - 1, expression);
                List<Integer> rightList = getCalculateResult(i + 1, right, expression);

                for (Integer l : leftList) {
                    for (Integer r : rightList) {
                        if (expression.charAt(i) == '+') {
                            result.add(l + r);
                        } else if (expression.charAt(i) == '-') {
                            result.add(l - r);
                        } else if (expression.charAt(i) == '*') {
                            result.add(l * r);
                        }
                    }
                }
            }
        }

        return result;
    }
}