export default {
    accordiontab: {
        header: ({ props }) => ({
            class: [
                // Sizing
                ' pb-0',
                // 'mt-6',

                // Shape
                'border-x-0 border-b-0',

                // Color puts a border on the top
                // 'border border-surface-200 dark:border-surface-700',

                // State
                { 'select-none pointer-events-none cursor-default opacity-60': props?.disabled }
            ]
        }),
        headerAction: ({ context }) => ({
            class: [
                //Font
                'font-semibold',
                'leading-7',
                
                //MY CUSTOMS
                'py-6', //Added myself: changes height of the Accordion headerTitle container
                'mx-12',  //Changes margin spacing on x-axis of the header container

                // Alignments
                'flex items-center justify-between flex-row-reverse',
                'relative',

                // Shape
                'rounded-md',

                // Color
                'bg-transparent',
                'text-surface-900 dark:text-surface-0',

                // States
                'focus:outline-none focus:outline-offset-0 focus-visible:ring-2 focus-visible:ring-primary-600 ring-inset dark:focus-visible:ring-primary-500', // Focus

                // Misc
                'cursor-pointer no-underline select-none'
            ]
        }),
        headerIcon: {
            class: 'inline-block ml-2'
        },
        headerTitle: {
            class: [
                'leading-7', //default
                
                
            ]
        },
        content: {
            class: [
                // Font
                'leading-7',

                // Spacing
                //for some reason needs a border on the bottom top stop it from closing to the nearest element
                'border-b-2 border-t-2 border-t-slate-600',

                // Color
                'text-surface-600 dark:text-surface-0/70'
            ]
        },
        transition: {
            enterFromClass: 'max-h-0',
            enterActiveClass: 'overflow-hidden transition-[max-height] duration-1000 ease-[cubic-bezier(0.42,0,0.58,1)]',
            enterToClass: 'max-h-[1000px]',
            leaveFromClass: 'max-h-[1000px]',
            leaveActiveClass: 'overflow-hidden transition-[max-height] duration-[450ms] ease-[cubic-bezier(0,1,0,1)]',
            leaveToClass: 'max-h-0'
        }
    }
};
