// Bob AI Prompt Website - Interactive Features
// IBM Think 2026

document.addEventListener('DOMContentLoaded', function() {
    // Initialize copy functionality for all copy buttons
    initializeCopyButtons();
    
    // Add smooth scrolling for anchor links
    initializeSmoothScrolling();
    
    // Add active state to navigation based on current page
    highlightActiveNavigation();
});

/**
 * Initialize copy to clipboard functionality for all copy buttons
 */
function initializeCopyButtons() {
    const copyButtons = document.querySelectorAll('.copy-btn');
    
    copyButtons.forEach(button => {
        button.addEventListener('click', function() {
            const promptId = this.getAttribute('data-prompt');
            const promptElement = document.getElementById(promptId);
            
            if (promptElement) {
                const promptText = promptElement.textContent.trim();
                
                // Copy to clipboard
                copyToClipboard(promptText, this);
            }
        });
    });
}

/**
 * Copy text to clipboard and provide visual feedback
 * @param {string} text - Text to copy
 * @param {HTMLElement} button - Button element to update
 */
function copyToClipboard(text, button) {
    // Modern clipboard API
    if (navigator.clipboard && navigator.clipboard.writeText) {
        navigator.clipboard.writeText(text)
            .then(() => {
                showCopySuccess(button);
            })
            .catch(err => {
                console.error('Failed to copy text: ', err);
                fallbackCopyToClipboard(text, button);
            });
    } else {
        // Fallback for older browsers
        fallbackCopyToClipboard(text, button);
    }
}

/**
 * Fallback copy method for older browsers
 * @param {string} text - Text to copy
 * @param {HTMLElement} button - Button element to update
 */
function fallbackCopyToClipboard(text, button) {
    const textArea = document.createElement('textarea');
    textArea.value = text;
    textArea.style.position = 'fixed';
    textArea.style.left = '-999999px';
    textArea.style.top = '-999999px';
    document.body.appendChild(textArea);
    textArea.focus();
    textArea.select();
    
    try {
        const successful = document.execCommand('copy');
        if (successful) {
            showCopySuccess(button);
        } else {
            showCopyError(button);
        }
    } catch (err) {
        console.error('Fallback: Failed to copy', err);
        showCopyError(button);
    }
    
    document.body.removeChild(textArea);
}

/**
 * Show success feedback when text is copied
 * @param {HTMLElement} button - Button element to update
 */
function showCopySuccess(button) {
    const originalText = button.textContent;
    button.textContent = '✓ Copied!';
    button.classList.add('copied');
    
    // Reset button after 2 seconds
    setTimeout(() => {
        button.textContent = originalText;
        button.classList.remove('copied');
    }, 2000);
}

/**
 * Show error feedback if copy fails
 * @param {HTMLElement} button - Button element to update
 */
function showCopyError(button) {
    const originalText = button.textContent;
    button.textContent = '✗ Failed';
    button.style.background = '#da1e28';
    
    // Reset button after 2 seconds
    setTimeout(() => {
        button.textContent = originalText;
        button.style.background = '';
    }, 2000);
}

/**
 * Initialize smooth scrolling for anchor links
 */
function initializeSmoothScrolling() {
    const links = document.querySelectorAll('a[href^="#"]');
    
    links.forEach(link => {
        link.addEventListener('click', function(e) {
            const targetId = this.getAttribute('href');
            
            if (targetId === '#') return;
            
            const targetElement = document.querySelector(targetId);
            
            if (targetElement) {
                e.preventDefault();
                targetElement.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });
}

/**
 * Highlight active navigation item based on current page
 */
function highlightActiveNavigation() {
    const currentPage = window.location.pathname.split('/').pop() || 'index.html';
    const navLinks = document.querySelectorAll('.nav-link');
    
    navLinks.forEach(link => {
        const linkPage = link.getAttribute('href');
        
        if (linkPage === currentPage || 
            (currentPage === '' && linkPage === 'index.html')) {
            link.classList.add('active');
        } else {
            link.classList.remove('active');
        }
    });
}

/**
 * Add animation to cards on scroll (optional enhancement)
 */
function initializeScrollAnimations() {
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };
    
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.opacity = '1';
                entry.target.style.transform = 'translateY(0)';
            }
        });
    }, observerOptions);
    
    // Observe all cards
    const cards = document.querySelectorAll('.use-case-card, .prompt-card, .step-card');
    cards.forEach(card => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(20px)';
        card.style.transition = 'opacity 0.5s ease, transform 0.5s ease';
        observer.observe(card);
    });
}

// Optional: Initialize scroll animations if desired
// Uncomment the line below to enable scroll animations
// initializeScrollAnimations();

/**
 * Mobile menu toggle (if needed for responsive design)
 */
function initializeMobileMenu() {
    const header = document.querySelector('.header');
    const nav = document.querySelector('.nav');
    
    // Create mobile menu button
    const menuButton = document.createElement('button');
    menuButton.className = 'mobile-menu-btn';
    menuButton.innerHTML = '☰';
    menuButton.style.display = 'none';
    
    // Add button to header
    const logo = document.querySelector('.logo');
    if (logo && window.innerWidth <= 768) {
        logo.parentElement.insertBefore(menuButton, nav);
        menuButton.style.display = 'block';
    }
    
    // Toggle menu on button click
    menuButton.addEventListener('click', () => {
        nav.classList.toggle('mobile-open');
    });
    
    // Handle window resize
    window.addEventListener('resize', () => {
        if (window.innerWidth > 768) {
            menuButton.style.display = 'none';
            nav.classList.remove('mobile-open');
        } else {
            menuButton.style.display = 'block';
        }
    });
}

// Initialize mobile menu
initializeMobileMenu();

// Add keyboard accessibility for copy buttons
document.addEventListener('keydown', function(e) {
    if (e.key === 'Enter' || e.key === ' ') {
        if (e.target.classList.contains('copy-btn')) {
            e.preventDefault();
            e.target.click();
        }
    }
});

// Log initialization
console.log('Bob AI Prompt Website initialized successfully!');
console.log('IBM Think 2026 - Experience Bob AI');

// Made with Bob
