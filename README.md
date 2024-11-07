# AI-Based Recruitment System

### Overview
This project is aimed at improving the recruitment process for Calon Pegawai Negeri Sipil (CPNS) and Pegawai Pemerintah dengan Perjanjian Kerja (PPPK) in government institutions. By leveraging Artificial Intelligence (AI), the system automates the selection process, ensuring faster, more accurate, and more efficient candidate screening and matching. The system integrates with government recruitment platforms like SSCASN, handles large data volumes, and prioritizes security and scalability.

### Key Features

* *Automated Screening:* Uses AI algorithms to match candidate qualifications (education, experience, certifications) with job descriptions.
* *Natural Language Processing (NLP):* Analyzes CVs and cover letters automatically to assess relevance to the job.
* *Test Scoring:* Integrates with government exam platforms (e.g., CAT for CPNS), automatically inputs and evaluates test results.
* *Position Matching:* Employs machine learning to predict candidate compatibility with available positions based on previous recruitment data.
* *Candidate Recommendations:* Provides HR and recruitment committees with recommendations for candidates best suited for interviews or further stages.


### Objectives

* *Increase Efficiency:* Automate and streamline the selection process to reduce administrative workload.
* *Reduce Bias:* Ensure transparency and objectivity in selection through AI-driven decisions based on qualifications and test results.
* *Optimize Resources:* Focus resources on the most suitable candidates, ensuring only the best are prioritized for final stages.
* *Improve Accuracy:* Provide a more precise matching system to align candidate skills and experience with job requirements.


## System Requirements
#### Functional Requirements
1. *Input Data:* 
     * Accept candidate data (biodata, education, work experience, test scores).
     * Integrate with CPNS and PPPK online systems for easy data input and document upload.
2. *Automated Screening:*
     * Apply AI-based screening to compare candidate qualifications with job descriptions.
     * Use NLP to analyze resumes and cover letters.
3. *Test-Based Evaluation:*
     * Integrate with online testing platforms and evaluate scores automatically.
     * Prioritize candidates with the highest test scores for further consideration.
4. *Position Matching:*
     * Machine learning algorithms predict compatibility between candidates and job positions.
5. *Candidate Grouping & Recommendations:*
   * Group candidates based on matching scores and generate automatic recommendations for further selection stages.

### Non-Functional Requirements

* Data Security: Implement encryption and comply with government standards like ISO 27001.
* Scalability: Handle large volumes of applications and process data in real-time.
* User-Friendly Interface: Provide easy-to-use interfaces for both candidates and HR teams.
* AI Accuracy: Train AI models with historical recruitment data to ensure high accuracy in matching.

## Infrastructure Requirements
* Cloud-based Platform: Deployed on cloud infrastructure for flexibility, high availability, and real-time processing.
* System Integration: Integrated with official government recruitment platforms, like SSCASN.
* Multi-device Support: Accessible on desktops and mobile devices for ease of use by candidates.

## Implementation Steps
1. *Detailed Requirements Analysis:* Analyze the current CPNS/PPPK recruitment process to identify areas for optimization.
2. *System Development & Testing:* Develop and test the AI-based application to ensure matching accuracy and user-friendliness.
3. *Government System Integration:* Ensure seamless integration with existing government recruitment platforms.

## 5W1H Analysis
* What: AI-based application to optimize CPNS and PPPK recruitment, automating screening, matching, and test result evaluation.
* Who:
   * Government Recruitment Teams (HRD): Use the system to screen and match candidates.
   * Applicants: Input data and follow the selection process via the app.
* Where: Integrated into government recruitment platforms like SSCASN, accessible from anywhere with supported devices.
* When: Used during the annual CPNS and PPPK recruitment cycles.
* Why:
   * Increase efficiency in the recruitment process.
   * Reduce human bias in candidate selection.
   * Optimize resource allocation by prioritizing top candidates.
   * Improve matching accuracy based on qualifications and test results.
* How: Utilizes AI, including NLP and machine learning, with data security ensured through encryption and scalable infrastructure via cloud services.

  ## Arsitektur Application
![Arsitektur drawio](https://github.com/user-attachments/assets/ff2d1efd-a081-40bf-9cd5-3127f5c8612b)
1. Frontend Layer
   * User Interface (Mobile Application): This is the user-facing part of the mobile application used by job applicants. The frontend includes key features:
     * Job Matching: Provides job matching between the applicant’s profile and the job openings from companies.
     * Company Funfact: Displays interesting facts about the company to attract applicants.
     * Profile: A space for applicants to manage and update their profiles.
2. Application Layer
   * AI Powered Modules: This layer contains AI-based modules that help process and analyze data from applicants and companies. These modules include
     * Automatic Screening (Machine Learning): Uses machine learning to automatically screen applicants based on certain criteria.
     * Position Matching (AI Matching Engine): Employs AI algorithms to match applicant qualifications with relevant job descriptions.
     * CV & Application Analysis (NLP): Utilizes natural language processing to analyze applicants’ CVs and cover letters for relevant keywords and patterns.
     * Test Result Evaluation (Automated Scoring): This module automates the scoring of applicants’ test results, such as CAT (Computer Assisted Test) and other competencies.
     * Database Management System: Acts as a bridge between the Application Layer and Data Layer, managing and accessing the data.
3. Data Layer
   * This layer stores the necessary data for the application, including:
     * Applicant Database: Data of applicants who apply through the platform.
     * Job Descriptions: Stores information on job positions available from companies.
     * Test Results (CAT, Competencies): Data on test results or assessments conducted for applicants.
     * User Activity Log: Records logs of user activities, such as interactions with the app and features used.

### System Workflow

1. Applicant Uses Mobile Application: The applicant opens the mobile app and accesses features like Job Matching, Company Funfact, or Profile.
2. Data Sent to Application Layer: Data from the Frontend Layer is sent to the Application Layer.
3 Processing in Application Layer: In the Application Layer, AI modules process the data for screening, matching, CV analysis, and test result evaluation.
4. Database Management System: The database management system stores and manages data in the Data Layer.
5. Storage in Data Layer: Essential data such as applicant profiles, job descriptions, test results, and user activity logs are stored in the Data Layer for future use.

## Use Case Diagram
![image](https://github.com/user-attachments/assets/ee1aeacd-4d7f-4482-aa44-01d41d0a2f66)

### Actors
  1. User: Represents the general user, likely an applicant who interacts with the application.
  2. Company: Represents a company or employer that provides job-related information for matching.
  3. IT Team: Represents the technical support team responsible for maintaining and repairing the system.

### Use Cases
1. Job Matching:
   * The User can access the Job Matching feature to find suitable job positions based on their profile and qualifications.
   * The Company is also involved, as they provide the job listings that are matched with users.

2. Check Company Funfact:
    * The User can access Company Funfact to view interesting facts or information about the company, which can make the company more appealing to potential applicants.

3. See Profile:
    * The User can view their profile, where they can manage or update their information.

4. Maintenance:
    * The IT Team is responsible for system maintenance, ensuring that the application runs smoothly and performs efficiently.

5. Repair:
    * The IT Team is also involved in the Repair process, addressing any technical issues or bugs that arise to keep the system operational.

This use case diagram shows how different actors interact with the system. The User primarily interacts with features that provide job information, company insights, and personal profile management. The Company plays a supportive role by providing data for job matching. Meanwhile, the IT Team ensures the system’s stability and functionality through maintenance and repair activities.
