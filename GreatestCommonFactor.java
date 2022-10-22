package gcm;

import quickSort.QuickSort;

import static java.lang.Math.abs;

//MUST BE PASSED AN INTEGER LIST IN ASCENDING ORDER.

/*
This code takes in a sorted integer array and will find the greatest common factor of all integers in array.

INPUTS:
    int[]; (SORTED)

OUTPUTS:
    greatestCommonFactor;



 */


public class GreatestCommonFactor {

    public static int GCM(int[] listOfNumbers){

        //smallest will be the smallest number in the list and we will use this to find common factors

        //We initialize it to one beacuse it will become overwritten within the first for loop.
        int smallest=1;
        //our return variable
        int largestCommonFactor=1;

        //This for loop will find check the numbers in the list, if one of them is 0, then there are no factors so we return 0, if any of the elements are 1 then the only factor is 1 so we return 1.
        /*We need to find the number with the lowest magnitude and use that to find the factors.
        There are three cases we need to consider (KEEP IN MIND THAT THE METHOD ASSUMES THE LIST IS SORTED IN ASCENDING ORDER) :
                        1. We have only positive numbers.
                        2. We have a mix of positive and negative numbers.
                        3. We have only negative numbers.
         After we have found the smallest value we break the for loop to ensure that we dont override our value.

        */
        int j;
        for(j=0;j<listOfNumbers.length;j++) {
            if (listOfNumbers[j] == 0) {
                return 0;
            } else if (listOfNumbers[j] == 1) {
                return 1;
            } else if (listOfNumbers[j] > 1 && j==0) {
                // CASE WHERE ALL VALUES IN THE LIST ARE POSITIVE.
                //Since the list is sorted if the first element is postive it will have the smallest magnitude.

                smallest = listOfNumbers[0];
                break;

            }else if(listOfNumbers[j]>1 && listOfNumbers[j-1] <0){
                //CASE WHERE WE HAVE BOTH NEGATIVE AND POSITIVE NUMBERS.
                //Since the list is sorted, if 2 consecutive numbers have different signs, then one of them must have the smallest magnitude.

                //We need to check if the postive or negative number has a smaller magnitude.
                if(listOfNumbers[j] > abs(listOfNumbers[j-1])){

                    //We set the factor to be positive because if for example we are given -2 and 4 to factor, the smallest would be -2 but we dont the factor to be -2 because then we factor the given numbers the sign will be flipped, to avoid this we will always return a postive factor.
                    smallest = abs(listOfNumbers[j-1]);
                    break;

                }else{
                    smallest = listOfNumbers[j];
                    break;
                }
            }



        }
        //CASE WHERE ONLY NEGATIVE
        //We do this case outside of the for loop because the for loop check all elements for positive numbers and if it doesnt find any then it incriments j and the only way it will get to the end (i.e. j = to the last index) then you know all values must be negative.
        //And do we can just assign smallest to the last element in the list.
        if(j==listOfNumbers.length-1){
            smallest = listOfNumbers[listOfNumbers.length-1];

        }
        //Once we have the smallest magnitude we will find the largest factor of it and check it it's a factor of all other numbers in the list.
        //If not we will check the next largest factor and check that. We will either find a factor that we can use or we will end up with 1 which means there are no common factors.


        //"i" will be the integer that we are checking if its is a factor of smallest so if smallest divided by i has a remainder of 0 it is a factor.
        //We start with i =smallest because the smallest might be factor of the rest and from the there we incriminant i down by 1 each time.

        int i =smallest;
        System.out.println("smallest = "+smallest);
        //smallest will always be postive (because it is an absolute value). and it will always the greater than 1 because if it isnt then this method wouldve already terminated. (FIRST 2 RETURN STATEMENTS).
        // So we can iterate "i' from smallest to 1 to check if any of the intermeditate integers are factors of all elements in the array.
        while(i>1){

            if(smallest % i ==0){
                int k=0;

                //We use "k" to denote the index in listOfNumbers, if listOfNumbers[k] !=0, then we know that i is not a common factor and so we break the while loop and reset k to 0, because there is no point in checking if "i" is a factor of the reamining elements.
                //if listOfNumbers[k] == 0 then we check the next k+1th element.

                while(k<listOfNumbers.length-1){
                    if(listOfNumbers[k] % i !=0){
                        System.out.println("k = "+k);
                        break;
                    }else{
                        k++;
                    }
                }
                //This if statement gets hit if the while "Naturally" terminates (i.e. the condition k<listOfNumbers.length is false) as opposed to us manually.
                //What this means is that "i" is a factor of every element in the list. And so we can set the largestCommonFactor = i and that is what we will return;

                if(k == listOfNumbers.length-1){
                    largestCommonFactor =i;
                    break;
                }


            }
            i--;
        }
        return largestCommonFactor;
    }
}
