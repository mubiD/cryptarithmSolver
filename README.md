Features

Cryptarithm solving: The module systematically checks all permutations of digits from 0 to 9 and determines which ones satisfy the equation.
Efficient pruning: The solver implements early exit conditions to reduce computation time by terminating when certain conditions are met, such as when intermediate sums exceed the target value.
Execution time measurement: The execution time is measured and displayed for performance benchmarking.
Valid digit assignment: The module avoids invalid solutions by ensuring certain letters (such as N, E, S, and W) do not map to 0, as they represent leading digits in multi-digit numbers.

Installation

1. Clone the repository to your local using: git clone https://github.com/yourusername/cryptarithm-solver.git
2. Compile the java files with maven using: mvn clean install
3. Run the CryptarithmSolver to find solutions

Usage

To solve the puzzle, call the solve() method. This method performs the following steps:

Generate Permutations: It generates all permutations of digits (0-9) for the letters in the equation.
Check Validity: Each permutation is checked to see if it satisfies the equation NORTH + EAST + SOUTH + WEST = EARTH, where each letter corresponds to a unique digit.
Output Results: For each valid solution, the module prints the corresponding numbers and their mappings. 

Algorithm Details

solve()
The entry point of the module. It initializes the letters and digits, and calls the generatePermutations method to start solving.
It also measures and prints the total execution time.

generatePermutations()
This method generates all possible permutations of the digits array, ensuring that no leading zeros are assigned to the letters N, E, S, or W.
It recursively calls itself to generate permutations for each index in the digits array, backtracking when necessary.

isValidSolution()
This method checks if a given permutation satisfies the cryptarithm equation NORTH + EAST + SOUTH + WEST = EARTH.
It computes the numeric values for NORTH, EAST, SOUTH, WEST, and EARTH based on the current digit assignments and checks if the sum of the first four equals the last.

printSolution()
This method prints the solution in a human-readable format, showing the equation and the corresponding digit mappings for the letters.

Performance Considerations

The module uses backtracking to generate permutations and applies pruning strategies:

Avoids leading zeros: The first digits of NORTH, EAST, SOUTH, and WEST are never assigned zero, which reduces invalid permutations early.
Early exit: If intermediate sums exceed the value of EARTH, the method terminates further computations for that permutation, reducing unnecessary checks.
