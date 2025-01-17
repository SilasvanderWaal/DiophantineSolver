Here's a structured and professional README for your Java project:  

---

# Diophantine Equation Solver  

## Overview  
The **Diophantine Equation Solver** is a Java-based program designed to solve brute force Diophantine equations with a large number of variables. Diophantine equations are polynomial equations where only integer solutions are of interest. This program simplifies the process by providing a template system for managing and solving equations efficiently.  

## Features  
- **Template System**:  
  - Choose a pre-defined template at startup.  
  - Each template lists variables, with one variable per line.  
- **Customizable Sum**:  
  - Enter the target sum the variables should add up to.  
- **Template Management**:  
  - Create new templates directly within the program.  
  - Switch between existing templates with ease.  
- **Efficient Brute Force Algorithm**:  
  - Capable of handling equations with a large number of variables.  

## How It Works  

1. **Startup**:  
   Upon launching the program, you will be prompted to select a template.  
   
2. **Template Selection**:  
   - Each template represents a set of variables (one per line).  
   - Templates help organize and simplify solving different equations.  

3. **Define Target Sum**:  
   - Input the target sum to which all variables in the selected template should add up.  

4. **Solve the Equation**:  
   - The program uses a brute force approach to find all integer solutions that satisfy the equation.  

5. **Template Management**:  
   - Create new templates with a custom list of variables.  
   - Switch between templates to work on different equations seamlessly.  

## Installation  

1. **Clone the Repository**:  
   ```bash  
   git clone https://github.com/SilasvanderWaal/DiophantineSolver/
   cd diophantine-solver  
   ```  
2. **Open and run in your favorite Java IDE**:  

## Usage  

### Example Workflow:  

1. **Select a Template**:  
   When prompted, choose a template that matches the variables in your equation.  

2. **Enter the Target Sum**:  
   Input the desired total the variables should add up to.  

3. **View Solutions**:  
   The program will display all integer solutions for the selected template and target sum.  

4. **Manage Templates**:  
   Use the template management system to:  
   - Add new templates.  
   - Switch to a different template.  

### Example Template Format:  
Templates are simple text files where each line represents a variable. For example:  
```  
x  
y  
z  
```  

This represents an equation with three variables.  
