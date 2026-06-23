# Assets Directory

This directory contains downloadable files for the IBM Bob Prompt Library website.

## GenApp Codebase

### Required File
Place the `GenApp.zip` file in this directory for the "Understand Codebase" use case.

### Creating GenApp.zip

1. Navigate to the directory containing your GenApp folder:
   ```bash
   cd "Understand Codebase download File"
   ```

2. Create the zip file:
   ```bash
   zip -r GenApp.zip GenApp/
   ```

3. Move the zip file to this assets directory:
   ```bash
   mv GenApp.zip /path/to/Bob-prompting/assets/
   ```

### Alternative: Using macOS Finder
1. Right-click on the `GenApp` folder
2. Select "Compress GenApp"
3. Rename the resulting file to `GenApp.zip`
4. Move it to this `assets` directory

### File Structure
The GenApp.zip should contain:
```
GenApp/
├── zOS Cobol/          (COBOL programs)
├── BMS/                (BMS maps)
├── JCL/                (JCL scripts)
├── Cobol Include/      (Copybooks)
├── Configuration/      (Config files)
├── Reports/            (Build reports)
├── PjKey.ini
├── GenApp_12_Jul_2024_12_59_35.txt
└── BatchBuildStatusFile_12_Jul_2024_12_59_35.txt
```

### Verification
After placing GenApp.zip in this directory, verify the download works:
1. Open `use-case-1.html` in a browser
2. Click the "Download Codebase" button
3. Confirm the zip file downloads successfully

### GitHub Pages Deployment
When deploying to GitHub Pages:
1. Commit the GenApp.zip file to your repository
2. Push to GitHub
3. The download link will automatically work on your GitHub Pages site

### File Size Considerations
- GitHub has a 100MB file size limit per file
- If GenApp.zip exceeds this, consider:
  - Using Git LFS (Large File Storage)
  - Hosting the file on IBM Cloud Object Storage
  - Splitting into smaller archives

### Security Note
Ensure the GenApp codebase does not contain:
- Sensitive credentials
- Production connection strings
- Proprietary business logic
- Personal information


## Sample Data for Analysis

### Required File
Place the `sample-data.csv` file in this directory for the "Exploratory Data Analysis" use case (4.2).

### Dataset Requirements
The sample dataset should be:
- **Format**: CSV or Excel (converted to CSV)
- **Size**: 100-1000 rows for quick analysis during the demo
- **Columns**: Mix of data types (numerical, categorical, dates)
- **Content**: Real-world business scenario data

### Suggested Dataset Types
Choose one of these scenarios:
1. **Sales Data**: date, product, category, quantity, revenue, region, customer_segment
2. **Customer Data**: customer_id, age, location, purchase_history, satisfaction_score, churn_status
3. **Product Inventory**: product_id, name, category, stock_level, price, supplier, last_restock_date
4. **Employee Data**: employee_id, department, salary, years_experience, performance_rating, location

### Creating sample-data.csv

**Option 1: Use existing dataset**
```bash
# Copy your CSV file
cp your-dataset.csv /path/to/Bob-prompting/assets/sample-data.csv
```

**Option 2: Convert Excel to CSV**
```bash
# If you have an Excel file, open it and save as CSV
# Or use a tool like pandas in Python:
# import pandas as pd
# df = pd.read_excel('your-file.xlsx')
# df.to_csv('sample-data.csv', index=False)
```

**Option 3: Generate synthetic data**
You can create a simple CSV with sample business data that includes:
- Interesting patterns or correlations
- A few missing values (to demonstrate data cleaning)
- Outliers (to show anomaly detection)
- Time series component (if applicable)

### Example CSV Structure
```csv
date,product,category,quantity,revenue,region,customer_segment
2024-01-01,Laptop,Electronics,5,4500,North,Enterprise
2024-01-01,Mouse,Accessories,20,400,South,SMB
2024-01-02,Keyboard,Accessories,15,750,East,Enterprise
...
```

### Verification
After placing sample-data.csv in this directory:
1. Open `use-case-4.html` in a browser
2. Click the "Download Sample CSV Data" button
3. Confirm the CSV file downloads successfully
4. Open the CSV to verify it contains appropriate data

### GitHub Pages Deployment
- Commit sample-data.csv to your repository
- File should be under 100MB (CSV files are typically small)
- Download link in use-case-4.html will work automatically

### Privacy & Security
Ensure the sample dataset does NOT contain:
- Real customer names or contact information
- Actual financial data or account numbers
- Proprietary business metrics
- Any personally identifiable information (PII)

Use anonymized, synthetic, or publicly available datasets only.
This is a demo/training codebase for IBM Think 2026 event purposes only.