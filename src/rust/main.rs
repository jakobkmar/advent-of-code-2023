use std::fs::{self};

fn main() {
    let input = fs::read_to_string("input/Day01.txt")
        .expect("The input file should be present");

    let number_names =
        vec!["one", "two",  "three",  "four",  "five",  "six",  "seven",  "eight",  "nine"];

    let result = input
        .lines()
        .map(|line| {
            let mut nums = vec![];
            for (c_index, c) in line.chars().enumerate() {
                if c.is_ascii_digit() {
                    nums.push(c.to_string().parse::<u32>().unwrap());
                }
                for (index, name) in number_names.iter().enumerate() {
                    if line[c_index..].starts_with(name) {
                        nums.push((index + 1) as u32);
                    }
                }
            }
            format!("{}{}", nums.first().unwrap(), nums.last().unwrap())
                .parse::<u32>().unwrap()
        })
        .sum::<u32>();

    println!("The result is {result}");
}
