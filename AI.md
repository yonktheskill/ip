# AI Usage Record

This document records the use of AI tools in developing the Evan chatbot project.

---

## Increment: A-UserGuide

**Date:** February 19-21, 2026  
**AI Tool:** GitHub Copilot (Chat) with Claude Sonnet 4.5  
**Task:** Create comprehensive user guide documentation in README.md

### What Worked Well ✅

1. **Automated Code Analysis**
    - The AI successfully explored the codebase by reading command classes (`AddCommand`, `DeleteCommand`, `FindCommand`, `UndoCommand`, etc.)
    - Examined task types (`ToDo`, `Deadline`, `Event`) to understand data formats
    - Analyzed `Parser.java` to identify all supported commands and their syntax

2. **Documentation Generation**
    - Generated a well-structured user guide with clear sections:
        - Quick Start instructions
        - Adding Tasks (ToDo, Deadline, Event)
        - Managing Tasks (List, Mark, Unmark, Delete)
        - Finding Tasks
        - Other Features (Undo, Exit)
    - Created a command summary table for quick reference
3. **Accuracy and Detail**
    - Correctly identified date/time format requirements (`yyyy-MM-dd HHmm`)
    - Provided accurate command syntax and examples
    - Generated realistic expected outputs based on code analysis
    - Included important notes about task numbering, auto-save, and undo functionality

### What Didn't Work / Issues ❌

1. **Implementation Details**
    - Some details about the implementation weren't written properly and needed manual changing
    - Required minor adjustments to ensure accuracy and completeness

### Time Saved ⏱️

**Estimated time saved: 1.5-2 hours**

**Manual approach would have required:**

- Reading through all command files individually: ~20 minutes
- Understanding task types and their formats: ~10 minutes
- Writing structured documentation: ~45 minutes
- Creating examples and expected outputs: ~30 minutes
- Formatting and organizing: ~15 minutes
- **Total: ~2 hours**

**With AI assistance:**

- AI explored codebase automatically: ~5 minutes
- Generated comprehensive documentation: ~2 minutes
- Manual review and adjustments: ~15 minutes
- **Total: ~20 minutes**

**Net time savings: ~1 hour 40 minutes (80% faster)**

### Overall Assessment 📊

The AI tool was highly effective for this documentation task. It excelled at:

- Systematically exploring the codebase
- Generating well-formatted, comprehensive documentation
- Maintaining consistency across all command descriptions
- Providing accurate technical details

While some implementation details required manual adjustments, the overall experience demonstrated significant productivity gains for documentation tasks.

**Recommendation:** Continue using AI assistance for documentation and code exploration tasks, while reviewing and refining the generated content for accuracy.
