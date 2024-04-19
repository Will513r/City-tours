/** @type {import('tailwindcss').Config} */

module.exports = {
  content: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  
  theme: {
    extend: {
      colors: {
        'primary-50': 'rgb(var(--primary-50))',
        'primary-100': 'rgb(var(--primary-100))',
        'primary-200': 'rgb(var(--primary-200))',
        'primary-300': 'rgb(var(--primary-300))',
        'primary-400': 'rgb(var(--primary-400))',
        'primary-500': 'rgb(var(--primary-500))',
        'primary-600': 'rgb(var(--primary-600))',
        'primary-700': 'rgb(var(--primary-700))',
        'primary-800': 'rgb(var(--primary-800))',
        'primary-900': 'rgb(var(--primary-900))',
        'primary-950': 'rgb(var(--primary-950))',
        'surface-0': 'rgb(var(--surface-0))',
        'surface-50': 'rgb(var(--surface-50))',
        'surface-100': 'rgb(var(--surface-100))',
        'surface-200': 'rgb(var(--surface-200))',
        'surface-300': 'rgb(var(--surface-300))',
        'surface-400': 'rgb(var(--surface-400))',
        'surface-500': 'rgb(var(--surface-500))',
        'surface-600': 'rgb(var(--surface-600))',
        'surface-700': 'rgb(var(--surface-700))',
        'surface-800': 'rgb(var(--surface-800))',
        'surface-900': 'rgb(var(--surface-900))',
        'surface-950': 'rgb(var(--surface-950))',
        'background': 'rgba(220, 228, 239, 1)',
        'boxes': 'rgba(225, 243, 248, 1)',
        'background1': 'rgba(241, 241, 241, 1)',
        'add': 'rgba(4, 107, 153, 1)',
        'ringred': 'rgba(152, 27, 30, 1)',
        'lightgray': 'rgba(220, 228, 239, 1)',
        'warmgray': 'rgba(73, 68, 64, 1)',
        'darkred': 'rgba(227, 28, 61, 1)',
        'lightred': 'rgba(249, 222, 222, 1)',
        'bahama': 'rgba(0, 88, 153, 1)'

      },
      fontFamily: {
        body: ['Montserrat', 'sans-serif'],
        head: ['Lora']
      }
    },
  },
  variants: {
    extend: {},
  },
  plugins: [

  ],
};

