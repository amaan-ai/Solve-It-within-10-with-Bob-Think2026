# Bob AI Prompt Library - IBM Think 2026

A professional, interactive webpage showcasing Bob AI coding tool use cases and prompts for the IBM Think 2026 event.

## 🎯 Purpose

This website provides hands-on prompts for users to experience Bob AI's capabilities across different use cases during a 10-minute interactive session at IBM Think 2026.

## 📋 Use Cases

1. **Understanding Codebase** - Analyze applications, extract business rules, and generate documentation
2. **SDLC Development** - Create applications from scratch using Bob (Coming Soon)
3. **Codebase Migration** - Migrate legacy systems to modern platforms (Coming Soon)
4. **Creative Use Cases** - Build games and perform data analysis (Coming Soon)

## 🚀 Quick Start

### Local Development

1. Clone this repository
2. Open `index.html` in your web browser
3. No build process required - it's a static website!

### Testing Locally

Simply open the `index.html` file in any modern web browser:
```bash
# On macOS
open index.html

# On Linux
xdg-open index.html

# On Windows
start index.html
```

Or use a local server:
```bash
# Using Python 3
python -m http.server 8000

# Using Node.js (http-server)
npx http-server

# Using PHP
php -S localhost:8000
```

Then navigate to `http://localhost:8000`

## 📦 Deployment

### GitHub Pages

1. **Push to GitHub:**
   ```bash
   git init
   git add .
   git commit -m "Initial commit: Bob AI Prompt Library"
   git branch -M main
   git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git
   git push -u origin main
   ```

2. **Enable GitHub Pages:**
   - Go to your repository settings
   - Navigate to "Pages" section
   - Under "Source", select "main" branch
   - Select "/ (root)" folder
   - Click "Save"
   - Your site will be available at: `https://YOUR_USERNAME.github.io/YOUR_REPO/`

### IBM Cloud Code Engine (Alternative)

1. **Create a Dockerfile:**
   ```dockerfile
   FROM nginx:alpine
   COPY . /usr/share/nginx/html
   EXPOSE 80
   CMD ["nginx", "-g", "daemon off;"]
   ```

2. **Deploy to Code Engine:**
   ```bash
   # Build and push container
   ibmcloud ce project create --name bob-prompts
   ibmcloud ce application create --name bob-prompts --build-source . --port 80
   ```

## 📁 Project Structure

```
.
├── index.html              # Main landing page
├── use-case-1.html        # Understanding Codebase prompts (with download)
├── use-case-2.html        # SDLC Development prompts
├── use-case-3.html        # Codebase Migration (placeholder)
├── use-case-4.html        # Creative Use Cases (placeholder)
├── styles.css             # IBM Think-inspired styling
├── script.js              # Interactive features & copy functionality
├── assets/
│   ├── GenApp.zip         # Sample codebase for download (add this file)
│   └── README.md          # Instructions for adding GenApp.zip
└── README.md              # This file
```

## ✨ Features

- **Professional IBM Think-inspired Design** - Clean, modern UI matching IBM's design language
- **Responsive Layout** - Works seamlessly on desktop, tablet, and mobile devices
- **One-Click Copy** - Copy prompts to clipboard with visual feedback
- **Downloadable Codebase** - Sample GenApp codebase for hands-on practice
- **Modular Structure** - Each use case has its own dedicated page
- **Easy Navigation** - Sticky header with clear navigation between sections
- **Accessible** - Keyboard navigation support and semantic HTML

## 🎨 Customization

### Adding the GenApp Codebase

1. **Create the zip file:**
   ```bash
   cd "Understand Codebase download File"
   zip -r GenApp.zip GenApp/
   mv GenApp.zip /path/to/Bob-prompting/assets/
   ```

2. **Verify the download:**
   - Open `use-case-1.html` in a browser
   - Click "Download Codebase" button
   - Confirm the file downloads

3. **For GitHub Pages:**
   - Commit and push the GenApp.zip file
   - The download will work automatically

See [`assets/README.md`](assets/README.md) for detailed instructions.

### Adding New Prompts

To add prompts to use cases 2, 3, or 4, edit the respective HTML files:

1. Open the use case file (e.g., `use-case-2.html`)
2. Replace the placeholder content with actual prompts
3. Follow this structure:

```html
<div class="prompt-card">
    <div class="prompt-header">
        <h2 class="prompt-title">Your Prompt Title</h2>
        <button class="copy-btn" data-prompt="prompt-id">Copy Prompt</button>
    </div>
    <div class="prompt-content" id="prompt-id">Your prompt text here</div>
</div>
```

### Styling Changes

Edit `styles.css` to customize:
- Colors (CSS variables in `:root`)
- Spacing and layout
- Typography
- Component styles

## 🔧 Technical Details

- **No Dependencies** - Pure HTML, CSS, and JavaScript
- **Modern Browser Support** - Works on all modern browsers
- **IBM Plex Font** - Uses IBM's official font family
- **Clipboard API** - Modern clipboard functionality with fallback
- **Mobile-First** - Responsive design principles

## 📝 Adding Content

### For Use Case 2 (SDLC Development)
Replace the placeholder in `use-case-2.html` with prompts for:
- Requirements gathering
- Architecture design
- Code implementation
- Testing strategies

### For Use Case 3 (Codebase Migration)
Replace the placeholder in `use-case-3.html` with prompts for:
- Legacy code analysis
- Migration planning
- Code transformation
- Platform adaptation

### For Use Case 4 (Creative Use Cases)
Replace the placeholder in `use-case-4.html` with prompts for:
- Game development
- Data analysis
- Visualization creation

## 🤝 Contributing

To add or modify prompts:
1. Edit the appropriate HTML file
2. Test locally
3. Commit and push changes
4. GitHub Pages will automatically update

## 📄 License

© 2026 IBM Think. All rights reserved.

## 🎪 Event Information

**IBM Think 2026**
- Website: https://www.ibm.com/events/think
- Experience: 10-minute hands-on sessions with Bob AI
- Format: Users select prompts and test them on provided laptops

## 💡 Tips for Event Hosts

1. **Pre-load the website** on all demo laptops
2. **Bookmark Use Case 1** as the starting point
3. **Test copy functionality** before the event
4. **Have Bob AI ready** on all machines
5. **Encourage experimentation** - users can modify prompts

## 🐛 Troubleshooting

### Copy button not working
- Ensure you're using HTTPS or localhost
- Check browser console for errors
- Try the fallback copy method

### Styling issues
- Clear browser cache
- Ensure `styles.css` is loaded
- Check browser compatibility

### Navigation not working
- Verify all HTML files are in the same directory
- Check file names match exactly (case-sensitive)

## 📞 Support

For issues or questions during IBM Think 2026, contact the event organizers.

---

Built with ❤️ for IBM Think 2026