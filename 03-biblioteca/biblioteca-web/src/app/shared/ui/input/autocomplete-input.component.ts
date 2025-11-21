import { Component, ElementRef, EventEmitter, HostListener, Input, OnChanges, Output, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-autocomplete-input',
  templateUrl: './autocomplete-input.component.html',
  styleUrls: ['./autocomplete-input.component.css']
})
export class AutocompleteInputComponent implements OnChanges {

  @Input() options: string[] = [];
  @Input() id: string = '';
  @Input() placeholder: string = '';
  @Input() selectedOption: string = '';

  @Output() selectionChange = new EventEmitter<string>();

  filteredOptions: string[] = [];
  isDropdownOpen = false;
  readonly = false;

  constructor(private elementRef: ElementRef) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['selectedOption']) {
      this.readonly = this.selectedOption !== '';
      this.filteredOptions = [];
      this.isDropdownOpen = false;
    }
  }

  onInput(event: Event): void {
    if (this.readonly) return;

    const value = (event.target as HTMLInputElement).value ?? '';
    this.selectedOption = value;

    this.filteredOptions = this.options.filter(opt =>
      opt.toLowerCase().includes(value.toLowerCase())
    );

    this.isDropdownOpen = this.filteredOptions.length > 0;
  }

  selectOption(option: string): void {
    this.selectedOption = option;
    this.readonly = true;
    this.isDropdownOpen = false;
    this.selectionChange.emit(option);
  }

  clearSelection(): void {
    this.selectedOption = '';
    this.readonly = false;
    this.filteredOptions = [];
    this.isDropdownOpen = false;
    this.selectionChange.emit('');
  }

  onFocus(): void {
    if (this.readonly) return;
    this.filteredOptions = [...this.options];
    this.isDropdownOpen = true;
  }

  @HostListener('document:click', ['$event'])
  onClickOutside(event: Event) {
    const target = event.target as HTMLElement;
    const clickedInside = target.closest('.autocomplete-container');
    
    if (!clickedInside || clickedInside !== this.elementRef.nativeElement.querySelector('.autocomplete-container')) {
      this.isDropdownOpen = false;
    }
  }

  @HostListener('document:keydown', ['$event'])
  handleKeydown(event: KeyboardEvent) {
    if (event.key === 'Escape' && this.isDropdownOpen) {
      this.isDropdownOpen = false;
    }
  }
}
